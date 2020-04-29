package com.fms.fileUpload.services;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fms.fileUpload.document.EventSummary;
import com.fms.fileUpload.document.POCDetails;
import com.fms.fileUpload.repository.EventSummaryRepository;


public class SpringBatchReader implements ItemReader<EventSummary>, StepExecutionListener {
	private static final Logger log = LoggerFactory.getLogger(SpringBatchReader.class);

	// @Value("${event.file.name}")
	private String eventFileName;

	List<EventSummary> eventSummaryList = new ArrayList<>();
	EventSummary eventSummary = new EventSummary();
	@Autowired
	EventSummaryRepository eventSummaryRepository;

	@Override
	public EventSummary read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		log.info(" read process starts ");
		if (!eventSummaryList.isEmpty()) {
			return eventSummaryList.remove(0);
		}
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		eventFileName = "/home/ubuntu/JAVA_Projects/Inputs/OutreachEventSummary.xlsx";
		File file = new File(eventFileName);
		if (file.exists()) {
			try (InputStream in = new FileInputStream(file)) {
				Workbook workbook = WorkbookFactory.create(in);
				Sheet sheet = workbook.getSheetAt(0);

				sheet.forEach(row -> {
					if (row.getRowNum() != 0) {
						assignValue(row);
					}
				});

			} catch (FileNotFoundException e) {
				log.error("File Not Found Exception {}", e);
			} catch (IOException e) {
				log.error("Exception while reading {}", e);
			}
		} else {
			log.warn("File does not exists at path :: {}", eventFileName);
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		log.info("Step Status::{} , Step Name:: {} ", stepExecution.getExitStatus(), stepExecution.getStepName());
		return stepExecution.getExitStatus();
	}

	public void assignValue(Row row) {
		log.info(" assignValue process starts ");
		eventSummary = new EventSummary();
		POCDetails pocDetails = new POCDetails();

		eventSummary.setEvent_id(row.getCell(0).getStringCellValue());
		eventSummary.setEvent_month(row.getCell(1).getStringCellValue());
		eventSummary.setBase_location(row.getCell(2).getStringCellValue());
		eventSummary.setBeneficiary_name(row.getCell(3).getStringCellValue());
		eventSummary.setVenue_address(row.getCell(4).getStringCellValue());
		eventSummary.setCouncil_name(row.getCell(5).getStringCellValue());
		eventSummary.setProject(row.getCell(6).getStringCellValue());
		eventSummary.setCategory(row.getCell(7).getStringCellValue());
		eventSummary.setEventname(row.getCell(8).getStringCellValue());
		eventSummary.setEvent_description(row.getCell(9).getStringCellValue());
		eventSummary.setEvent_date(row.getCell(10).getStringCellValue());
		eventSummary.setTotal_no_of_volunteers((int) row.getCell(11).getNumericCellValue());
		eventSummary.setTotal_no_of_volunteers_hrs((int) row.getCell(12).getNumericCellValue());
		eventSummary.setTotal_travel_hrs((int) row.getCell(13).getNumericCellValue());
		eventSummary.setOverall_volunteer_hrs((int) row.getCell(14).getNumericCellValue());
		eventSummary.setLives_impacted((int) row.getCell(15).getNumericCellValue());
		eventSummary.setActivity_type((int) row.getCell(16).getNumericCellValue());
		eventSummary.setEvent_status(row.getCell(17).getStringCellValue());
		
		pocDetails.setPoc_id((int) row.getCell(18).getNumericCellValue());
		pocDetails.setPoc_name(row.getCell(19).getStringCellValue());
		pocDetails.setPoc_number((int) row.getCell(20).getNumericCellValue()); //

		eventSummary.setPocDetails(pocDetails);

		this.eventSummaryList.add(eventSummary);
	}

}