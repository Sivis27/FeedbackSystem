package com.fms.email.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fms.email.constants.FMSUtils;
import com.fms.email.entity.EmailInfo;
import com.fms.email.entity.EventSummary;
import com.fms.email.entity.GenerateReports;
import com.fms.email.exception.FMSEmailServiceException;
import com.fms.email.model.AuthRequest;
import com.fms.email.model.BulkEmail;
import com.fms.email.model.UserInfo;
import com.fms.email.repository.DownloadExcelRepository;
import com.fms.email.repository.EmailRepository;
import com.fms.email.repository.EventSummaryRepository;
import com.fms.email.service.SendEmailService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@CrossOrigin
public class EmailController {

	EmailInfo emailInfo = new EmailInfo();

	@Autowired
	DatabaseClient databaseClient;
	@Autowired
	EmailRepository emailRepository;
	@Autowired
	SendEmailService sendmail;
	@Autowired
	EventSummaryRepository eventDetailsRepository;
	@Autowired
	DownloadExcelRepository downloadExcelRepository;

	int rowNum = 1;
	String filePath = null;
	String filePathStr = null;

	@PostMapping("/mailForAdmin")
	public Mono<String> triggerEmailForAdminRole(@RequestBody AuthRequest ar) {

		// EVENTS_MAIL = "1"; EVENTSDETAILS_MAIL = "2"; REPORTS_MAIL = "3";

		emailInfo.setFromaddress("admin@admin.com");
		emailInfo.setSendon(new Date());

		if (ar.getEmailType().equalsIgnoreCase(FMSUtils.EVENTS_MAIL)) {
			// Bulk mail process
			try {
				databaseClient
						.execute(" select e.event_id ,e.employee_id,e.emailid \n" + "	from eventparticipantinfo e \n"
								+ "left join participantnotattended pnot \n"
								+ "on e.event_id = pnot.event_id and  e.employee_id = pnot.employeeid \n"
								+ "	left join participantunregistered unreg  \n"
								+ "	on e.event_id = unreg.event_id and  e.employee_id = unreg.employeeid \n"
								+ "	WHERE  pnot.event_id IS NULL AND unreg.event_id IS NULL ")
						.as(BulkEmail.class).fetch().all().subscribe(bulkEmail -> {
							emailInfo.setEventbody(FMSUtils.REPORTS_MAIL);
							emailInfo.setEventsubject(FMSUtils.EVENTS_MAIL);
							emailInfo.setEventid(bulkEmail.getEvent_id());
							////emailInfo.setToAddressList(bulkEmail.getEmailid());
							
							
							emailInfo.setEventname("Bulk Email for Participants ");
						});

				filePathStr = generateExcelReport(Integer.parseInt(FMSUtils.EVENTS_MAIL));
				this.sendmail.generateAndSendEmail(emailInfo, filePathStr);
			} catch (MessagingException e) {
				new FMSEmailServiceException(" Error in Email service " + FMSUtils.EVENTS_MAIL + e.getMessage());
			}
		} else if (ar.getEmailType().equalsIgnoreCase(FMSUtils.EVENTSDETAILS_MAIL)
				&& (ar.getEventid() != null && !ar.getEventid().isEmpty())) {
			try {
				eventDetailsRepository.findById(Long.parseLong(ar.getEventid())).subscribe(eventsList -> {
					emailInfo.setEventbody(eventsList.getEvent_description());
					emailInfo.setEventsubject("");
					emailInfo.setEventid(eventsList.getEvent_id());
				});
				filePathStr = generateExcelReport(Integer.parseInt(FMSUtils.EVENTSDETAILS_MAIL));
				this.sendmail.generateAndSendEmail(emailInfo, filePathStr);
			} catch (MessagingException e) {
				new FMSEmailServiceException(" Error in Email service " + FMSUtils.EVENTSDETAILS_MAIL + e.getMessage());
			}
		} else if (ar.getEmailType().equalsIgnoreCase(FMSUtils.REPORTS_MAIL)) {
			try {
				databaseClient.execute(
						" select  es.event_id ,es.event_month,es.baselocation, es.beneficiary_name, es.council_name , es.project, es.category, \n"
								+ " ep.employee_id,ep.employee_name,ep.businessunit,ep.event_status,ep.iiepcategory \n"
								+ " from eventsummary  es ,eventparticipantinfo ep \n"
								+ " where es.event_id = ep.event_id ")
						.as(GenerateReports.class).fetch().all().subscribe(dashboard -> {
							emailInfo.setEventbody(FMSUtils.REPORTS_MAIL);
							emailInfo.setEventsubject(FMSUtils.REPORTS_MAIL + " for ParticipantFeedback ");
							emailInfo.setEventid(dashboard.getEvent_id());
						});
				filePathStr = generateExcelReport(Integer.parseInt(FMSUtils.REPORTS_MAIL));
				this.sendmail.generateAndSendEmail(emailInfo, filePathStr);
			} catch (MessagingException e) {
				new FMSEmailServiceException(" Error in Email service " + FMSUtils.REPORTS_MAIL + e.getMessage());
			}
		}
		return Mono.just("Notifcation main sent succesfully ");

	}

	@PostMapping("/mailForPMO")
	public Mono<String> triggerEmailForPMORole() {
		try {
			databaseClient.execute(
					" select  es.event_id ,es.event_month,es.baselocation, es.beneficiary_name, es.council_name , es.project, es.category, \n"
							+ " ep.employee_id,ep.employee_name,ep.businessunit,ep.event_status,ep.iiepcategory \n"
							+ " from eventsummary  es ,eventparticipantinfo ep \n"
							+ " where es.event_id = ep.event_id ")
					.as(GenerateReports.class).fetch().all().subscribe(dashboard -> {
						emailInfo.setEventbody(FMSUtils.REPORTS_MAIL);
						emailInfo.setEventsubject(FMSUtils.REPORTS_MAIL + " for ParticipantFeedback ");
						emailInfo.setEventid(dashboard.getEvent_id());
					});
			filePathStr = generateExcelReport(Integer.parseInt(FMSUtils.REPORTS_MAIL));
			this.sendmail.generateAndSendEmail(emailInfo, filePathStr);
		} catch (MessagingException e) {
			new FMSEmailServiceException(
					" Error in triggerEmailForPMORole service " + FMSUtils.REPORTS_MAIL + e.getMessage());
		}
		return Mono.just("Notifcation mail sent succesfully for PMO role");

	}

	@PostMapping("/mailForPOC")
	public Mono<String> triggerEmailForPOCRole() {
		log.info(" Authnetican call for POC Email Services ");
		try {
			databaseClient.execute(
					" select  es.event_id ,es.event_month,es.baselocation, es.beneficiary_name, es.council_name , es.project, es.category, \n"
							+ " ep.employee_id,ep.employee_name,ep.businessunit,ep.event_status,ep.iiepcategory \n"
							+ " from eventsummary  es ,eventparticipantinfo ep \n"
							+ " where es.event_id = ep.event_id ")
					.as(GenerateReports.class).fetch().all().subscribe(dashboard -> {
						emailInfo.setEventbody(FMSUtils.REPORTS_MAIL);
						emailInfo.setEventsubject(FMSUtils.REPORTS_MAIL);
						emailInfo.setEventid(dashboard.getEvent_id());
					});
			filePathStr = generateExcelReport(Integer.parseInt(FMSUtils.REPORTS_MAIL));
			log.info(" Authnetican call for POC Email Services filePathStr  " + filePathStr);
			this.sendmail.generateAndSendEmail(emailInfo, filePathStr);
		} catch (MessagingException e) {
			new FMSEmailServiceException(
					" Error in triggerEmailForPOCRole service " + FMSUtils.REPORTS_MAIL + e.getMessage());
		}

		return Mono.just("Notifcation mail sent succesfully for POC role");
	}

	/*
	 * Download Excel part
	 */

	@PostMapping("/DownloadExcel/{id}")
	public Mono<String> exposeMono(@PathVariable("excelType") int excelType) {
		generateExcelReport(excelType);
		return Mono.just("Excel downloaded successfully ");
	}

	private String generateExcelReport(int excelType) {
		try {
			Workbook workbook = new XSSFWorkbook();
			CreationHelper createHelper = workbook.getCreationHelper();
			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			System.out.println(" generateExcelReport --> " + excelType);

			String sheetName = "";
			// EventSummary Details
			if (excelType == 1) {
				sheetName = "EventSummary";
				// Create a Sheet
				Sheet sheet = workbook.createSheet("EventSummaryInfo");
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 12);
				headerFont.setColor(IndexedColors.BLACK.getIndex());

				// Create a CellStyle with the font
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);

				String[] summary = { "Event Name", "Event Date", "Business Unit", "Status", "Venue Address",
						"Total Volunteers", "Total Volunteer Hours", "Total Travel Hours" };
				// Create a Row
				Row headerRow = sheet.createRow(0);
				// Create cells
				for (int i = 0; i < summary.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(summary[i]);
					cell.setCellStyle(headerCellStyle);
				}
				// Create Other rows and cells with employees data
				eventDetailsRepository.findAll().subscribe(consumer -> {
					consumer = new EventSummary();
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(consumer.getEventname());
					Cell eventDate = row.createCell(1);
					eventDate.setCellValue(consumer.getEvent_date());
					eventDate.setCellStyle(dateCellStyle);
					// row.createCell(2).setCellValue(consumer.get());
					row.createCell(3).setCellValue(consumer.getEvent_status());
					row.createCell(4).setCellValue(consumer.getVenue_address());
					row.createCell(5).setCellValue(consumer.getTotal_no_of_volunteers());
					row.createCell(6).setCellValue(consumer.getTotal_no_of_volunteers_hrs());
					row.createCell(7).setCellValue(consumer.getTotal_travel_hrs());
				});
				// Resize all columns to fit the content size
				for (int i = 0; i < summary.length; i++) {
					sheet.autoSizeColumn(i);
				}

				// Write the output to a file
				FileOutputStream fileOut = new FileOutputStream(
						"/home/ubuntu/JAVA_Projects/Excels/EventSummary/" + sheetName + ".xlsx");
				filePath = "/home/ubuntu/JAVA_Projects/Excels/EventSummary/" + sheetName + ".xlsx";
				workbook.write(fileOut);
				fileOut.close();
				// Closing the workbook
				workbook.close();
			}
			// UserInfo Excel

			if (excelType == 2) {
				sheetName = "UserInfo";
				// Create a Sheet
				Sheet sheet = workbook.createSheet("UserDetails");
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 12);
				headerFont.setColor(IndexedColors.BLACK.getIndex());

				// Create a CellStyle with the font
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);

				String[] userInfo = { "Email", "FirstName", "LastName", "Role Name", "Active User" };
				// Create a Row
				Row headerRow = sheet.createRow(0);
				// Create cells
				for (int i = 0; i < userInfo.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(userInfo[i]);
					cell.setCellStyle(headerCellStyle);
				}

				// Create Other rows and cells with employees data
				downloadExcelRepository.findAll().subscribe(user -> {
					user = new UserInfo();
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(user.getEmailid());
					row.createCell(1).setCellValue(user.getFirstname());
					row.createCell(2).setCellValue(user.getLastname());
					row.createCell(4).setCellValue(user.getRolename());
					row.createCell(5).setCellValue(user.isActiveuser());
				});

				// Resize all columns to fit the content size
				for (int i = 0; i < userInfo.length; i++) {
					sheet.autoSizeColumn(i);
				}

				// Write the output to a file
				FileOutputStream fileOut = new FileOutputStream(
						"/home/ubuntu/JAVA_Projects/Excels/UserInfo/" + sheetName + ".xlsx");
				filePath = "/home/ubuntu/JAVA_Projects/Excels/UserInfo/" + sheetName + ".xlsx";
				workbook.write(fileOut);
				fileOut.close();
				// Closing the workbook
				workbook.close();

			}

			// Reports Excel
			if (excelType == 3) {
				sheetName = "EventReports";
				// Create a Sheet
				Sheet sheet = workbook.createSheet("EventSummary");
				Font headerFont = workbook.createFont();
				headerFont.setBold(true);
				headerFont.setFontHeightInPoints((short) 12);
				headerFont.setColor(IndexedColors.BLACK.getIndex());

				// Create a CellStyle with the font
				CellStyle headerCellStyle = workbook.createCellStyle();
				headerCellStyle.setFont(headerFont);

				String[] report = { "Event ID", "Event Month", "Base Location","Beneficiary Name", "Council Name",
						"Project", "Category", "Employee ID","Employee Name", "Business Unit", "Event Status",
						  "IIEPCategory" };
				// Create a Row
				Row headerRow = sheet.createRow(0);
				// Create cells
				for (int i = 0; i < report.length; i++) {
					Cell cell = headerRow.createCell(i);
					cell.setCellValue(report[i]);
					cell.setCellStyle(headerCellStyle);
				}
				databaseClient.execute(
						" select  es.event_id ,es.event_month,es.baselocation, es.beneficiary_name,"
						+ " es.council_name , es.project, es.category, \n"
								+ " ep.employee_id,ep.employee_name,ep.businessunit,ep.event_status,ep.iiepcategory \n"
								+ " from eventsummary  es ,eventparticipantinfo ep \n"
								+ " where es.event_id = ep.event_id")
						.as(GenerateReports.class).fetch().all().subscribe(reports -> {
							// reports = new GenerateReports();
							Row row = sheet.createRow(rowNum++);
							row.createCell(0).setCellValue(reports.getEvent_id());
							row.createCell(1).setCellValue(reports.getEvent_month());
							row.createCell(2).setCellValue(reports.getBaselocation());
							row.createCell(3).setCellValue(reports.getBeneficiary_name());
							row.createCell(4).setCellValue(reports.getCouncil_name());
							row.createCell(5).setCellValue(reports.getProject());
							row.createCell(6).setCellValue(reports.getCategory());
							row.createCell(7).setCellValue(reports.getEmployee_id());
							row.createCell(8).setCellValue(reports.getEmployee_name());
							row.createCell(9).setCellValue(reports.getBusinessunit());
							row.createCell(10).setCellValue(reports.getEvent_status());
							row.createCell(11).setCellValue(reports.getIiepcategory());
						});

				// Write the output to a file
				FileOutputStream fileOut = new FileOutputStream(
						"/home/ubuntu/JAVA_Projects/Excels/EventReports/" + sheetName + ".xlsx");
				filePath = "/home/ubuntu/JAVA_Projects/Excels/EventReports/" + sheetName + ".xlsx";
				workbook.write(fileOut);
				fileOut.close();

				// Closing the workbook
				workbook.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}

	@GetMapping("/generateReports")
	public Flux<ResponseEntity<GenerateReports>> generateReports() {
		
		
		return databaseClient.execute(
				" select  es.event_id ,es.event_month,es.baselocation, es.beneficiary_name, es.council_name , es.project, es.category, \n"
						+ " ep.employee_id,ep.employee_name,ep.businessunit,ep.event_status,ep.iiepcategory \n"
						+ " from eventsummary  es ,eventparticipantinfo ep \n" + " where es.event_id = ep.event_id ")
				.as(GenerateReports.class).fetch().
				
				all().
				
				
				map(dashboard -> ResponseEntity.ok(dashboard))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();

	}
}
