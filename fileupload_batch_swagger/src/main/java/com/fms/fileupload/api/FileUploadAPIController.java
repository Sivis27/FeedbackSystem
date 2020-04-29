package com.fms.fileupload.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fms.fileupload.model.EventParticipantInfo;
import com.fms.fileupload.model.ParticipantEventUnregistered;
import com.fms.fileupload.model.ParticipantNotAttended;
import com.fms.fileupload.model.EventSummary;
import com.fms.fileupload.repository.OutReachEventsRepository;
import com.fms.fileupload.repository.OutReachParticipantsEventsRepository;
import com.fms.fileupload.repository.ParticipantEventUnregisteredRepository;
import com.fms.fileupload.repository.ParticipantNotAttendedRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-25T05:05:49.607+13:25")

@Slf4j
@Controller
public class FileUploadAPIController implements FileUploadAPI {

	@Autowired
	OutReachEventsRepository outReachEventsRepository;
	List<EventSummary> eventSummaryList;
	EventSummary eventSummary;

	@Autowired
	OutReachParticipantsEventsRepository participantsEventsRepository;
	List<EventParticipantInfo> participantInfoList;
	EventParticipantInfo eventParticipantInfo;

	@Autowired
	ParticipantEventUnregisteredRepository eventUnregisteredRepository;
	ParticipantEventUnregistered eventunregistered;
	List<ParticipantEventUnregistered> unregisterLsit;

	@Autowired
	ParticipantNotAttendedRepository notattendedRepository;
	ParticipantNotAttended participantNotAttended;
	List<ParticipantNotAttended> notattendedList;

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public FileUploadAPIController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<ObjectMapper> getObjectMapper() {
		return Optional.ofNullable(objectMapper);
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	public Mono<ResponseEntity<String>> apiEventInfo() {
		try {
			return Flux.fromStream(Files.walk(Paths.get("/home/ubuntu/JAVA_Projects/Inputs/OutReachEventInfo.xlsx")))
					.map(mapper -> {
						participantInfoList = new ArrayList<EventParticipantInfo>();
						XSSFWorkbook workbook = null;
						try {
							log.info("" + mapper.getFileName());
							if (mapper.getFileName().startsWith("OutReachEventInfo.xlsx")) {
								workbook = new XSSFWorkbook(mapper.toFile());
								XSSFSheet sheet = workbook.getSheetAt(0);
								sheet.forEach(row -> {
									// File invalid/Valid check
									// if true and valid file
									if (row.getRowNum() != 0) {
										// eventParticipantInfo = new EventParticipantInfo();
										eventParticipantInfo.setEvent_id(row.getCell(0).getStringCellValue());
										eventParticipantInfo.setEmailid(row.getCell(9).getStringCellValue());
										eventParticipantInfo.setBusinessunit(row.getCell(13).getStringCellValue());
										eventParticipantInfo.setEmployee_id((int) row.getCell(7).getNumericCellValue());
										eventParticipantInfo.setEmployee_name(row.getCell(8).getStringCellValue());
										eventParticipantInfo.setEvent_status(row.getCell(14).getStringCellValue());
										// Once verrified success - then set to boolean flag
										// eventParticipantInfo.setFbe_status();
										eventParticipantInfo.setIiepcategory(row.getCell(15).getStringCellValue());
										eventParticipantInfo
												.setLivesimpacted((int) row.getCell(12).getNumericCellValue());
										eventParticipantInfo.setTravelhrs((int) row.getCell(11).getNumericCellValue());
										eventParticipantInfo
												.setVolunteerhrs((int) row.getCell(10).getNumericCellValue());
										participantInfoList.add(eventParticipantInfo);
									}
								});
							}
							workbook.close();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
							Path destinationepath = Paths.get("/home/ubuntu/JAVA_Projects/Outputs/"
									+ mapper.getFileName() + "" + dateFormat.format(date) + ".xlsx");
							Files.copy(mapper, destinationepath, StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return Flux.just(this.participantsEventsRepository.saveAll(participantInfoList).subscribe())
								.then(Mono.just(ResponseEntity.status(HttpStatus.OK)
										.body("participantsEventsRepository successfully uploaded on FMS")));
					}).then(Mono.just(
							ResponseEntity.status(HttpStatus.OK).body("eventParticipantInfo successfully uploaded ")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Mono.just(ResponseEntity.status(HttpStatus.OK).body("eventParticipantInfo successfully "));

	}

	public Mono<ResponseEntity<String>> apiEventSummary() {

		try {
			return Flux.fromStream(Files.walk(Paths.get("/home/ubuntu/JAVA_Projects/Inputs/OutreachEventSummary.xlsx")))
					.map(mapper -> {
						eventSummaryList = new ArrayList<EventSummary>();
						XSSFWorkbook workbook = null;
						try {
							log.info(" OutreachEventSummary - " + mapper.getFileName());
							if (mapper.getFileName().startsWith("OutreachEventSummary.xlsx")) {
								workbook = new XSSFWorkbook(mapper.toFile());
								XSSFSheet sheet = workbook.getSheetAt(0);
								sheet.forEach(row -> {
									// File invalid/Valid check
									// if true and valid file
									if (row.getRowNum() != 0) {
										eventSummary = new EventSummary();
										eventSummary.setEvent_id(row.getCell(0).getStringCellValue());
										eventSummary.setEvent_month(row.getCell(1).getStringCellValue());
										eventSummary.setBaselocation(row.getCell(2).getStringCellValue());
										eventSummary.setBeneficiary_name(row.getCell(3).getStringCellValue());
										eventSummary.setVenue_address(row.getCell(4).getStringCellValue());
										eventSummary.setCouncil_name(row.getCell(5).getStringCellValue());
										eventSummary.setProject(row.getCell(6).getStringCellValue());
										eventSummary.setCategory(row.getCell(7).getStringCellValue());
										eventSummary.setEventname(row.getCell(8).getStringCellValue());
										eventSummary.setEvent_description(row.getCell(9).getStringCellValue());
										eventSummary.setEvent_date(row.getCell(10).getStringCellValue());
										eventSummary
												.setTotal_no_of_volunteers((int) row.getCell(11).getNumericCellValue());
										eventSummary.setTotal_no_of_volunteers_hrs(
												(int) row.getCell(12).getNumericCellValue());
										eventSummary.setTotal_travel_hrs((int) row.getCell(13).getNumericCellValue());
										eventSummary
												.setOverall_volunteer_hrs((int) row.getCell(14).getNumericCellValue());
										eventSummary.setLives_impacted((int) row.getCell(15).getNumericCellValue());
										eventSummary.setActivity_type((int) row.getCell(16).getNumericCellValue());
										eventSummary.setEvent_status(row.getCell(17).getStringCellValue());
										eventSummary.setPoc_id((int) row.getCell(18).getNumericCellValue());
										eventSummary.setPoc_name(row.getCell(19).getStringCellValue());
										eventSummary.setPoc_number((int) row.getCell(20).getNumericCellValue());
										eventSummaryList.add(eventSummary);

									}
								});
							}
							workbook.close();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
							Path destinationepath = Paths.get("/home/ubuntu/JAVA_Projects/Outputs/"
									+ mapper.getFileName() + "" + dateFormat.format(date) + ".xlsx");
							Files.copy(mapper, destinationepath, StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							log.info("Exception event summary saved successfully " + e.getMessage());
						}

						return Flux.just(this.outReachEventsRepository.saveAll(eventSummaryList).subscribe()).then(
								Mono.just(ResponseEntity.status(HttpStatus.OK).body(" event summary uploaded done ")));
					}).map(map -> {

						log.info("event summary saved successfully ");
						return map;
					}).then(Mono.just(ResponseEntity.status(HttpStatus.OK).body("evet summary uploaded all")));

		} catch (IOException e) {
			log.info("IOException event summary saved successfully " + e.getMessage());
		}
		return Mono.just(ResponseEntity.status(HttpStatus.OK).body("event summary uploaded ended "));

	}

	public Mono<ResponseEntity<String>> participantNotAttended() {
		try {
			return Flux.fromStream(Files.walk(Paths.get("/home/ubuntu/JAVA_Projects/Inputs/VolunteerNotAttended.xlsx")))
					.map(mapper -> {
						notattendedList = new ArrayList<ParticipantNotAttended>();
						XSSFWorkbook workbook = null;
						try {
							log.info("participantNotAttended file name " + mapper.getFileName());
							if (mapper.getFileName().startsWith("VolunteerNotAttended.xlsx")) {

								workbook = new XSSFWorkbook(mapper.toFile());
								XSSFSheet sheet = workbook.getSheetAt(0);
								sheet.forEach(row -> {
									// if true and valid file
									if (row.getRowNum() != 0) {
										// participantNotAttended = new ParticipantNotAttended();
										participantNotAttended.setEvent_id(row.getCell(0).getStringCellValue());
										participantNotAttended.setEventname(row.getCell(1).getStringCellValue());
										participantNotAttended.setBeneficiaryname(row.getCell(2).getStringCellValue());
										participantNotAttended.setBaselocation(row.getCell(3).getStringCellValue());
										participantNotAttended.setEventdate(row.getCell(4).getStringCellValue());
										participantNotAttended
												.setEmployeeid((int) row.getCell(5).getNumericCellValue());
										notattendedList.add(participantNotAttended);
									}
								});
							}
							workbook.close();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
							Path destinationepath = Paths.get("/home/ubuntu/JAVA_Projects/Outputs/"
									+ mapper.getFileName() + "" + dateFormat.format(date) + ".xlsx");
							Files.copy(mapper, destinationepath, StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							log.info("Exception in participantNotAttended " + e.getMessage());
						}
						return Flux.just(this.notattendedRepository.saveAll(notattendedList).subscribe()).then(Mono
								.just(ResponseEntity.status(HttpStatus.OK).body("participantNotAttended uploaded")));
					}).map(map -> {
						log.info("participantNotAttended successfully");
						return map;
					})
					.then(Mono.just(ResponseEntity.status(HttpStatus.OK).body("participantNotAttended uploaded all")));
		} catch (IOException e) {
			log.info("IOException in participantNotAttended " + e.getMessage());
		}
		return Mono.just(ResponseEntity.status(HttpStatus.OK).body("participantNotAttended uploaded ended"));
	}

	public Mono<ResponseEntity<String>> participantUnregisterd() {
		try {
			return Flux
					.fromStream(Files.walk(Paths.get("/home/ubuntu/JAVA_Projects/Inputs/VolunteerUnregistered.xlsx")))
					.map(mapper -> {
						unregisterLsit = new ArrayList<ParticipantEventUnregistered>();
						XSSFWorkbook workbook = null;
						try {
							log.info("participantUnregisterd file name " + mapper.getFileName());
							if (mapper.getFileName().startsWith("VolunteerUnregistered.xlsx")) {
								workbook = new XSSFWorkbook(mapper.toFile());
								XSSFSheet sheet = workbook.getSheetAt(0);
								sheet.forEach(row -> {
									if (row.getRowNum() != 0) {
										// eventunregistered = new ParticipantEventUnregistered();
										eventunregistered.setEvent_id(row.getCell(0).getStringCellValue());
										eventunregistered.setEventname(row.getCell(1).getStringCellValue());
										eventunregistered.setBeneficiaryname(row.getCell(2).getStringCellValue());
										eventunregistered.setBaselocation(row.getCell(3).getStringCellValue());
										eventunregistered.setEventdate(row.getCell(4).getStringCellValue());
										eventunregistered.setEmployeeid((int) row.getCell(5).getNumericCellValue());
										unregisterLsit.add(eventunregistered);
									}
								});
							}
							workbook.close();
							Date date = new Date();
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
							Path destinationepath = Paths.get("/home/ubuntu/JAVA_Projects/Outputs/"
									+ mapper.getFileName() + "" + dateFormat.format(date) + ".xlsx");
							Files.copy(mapper, destinationepath, StandardCopyOption.REPLACE_EXISTING);
						} catch (Exception e) {
							log.info("Exception in participant unregister  " + e.getMessage());
						}
						return Flux.just(this.eventUnregisteredRepository.saveAll(unregisterLsit).subscribe())
								.then(Mono.just(ResponseEntity.status(HttpStatus.OK)
										.body("successfully participant unregister uploaded in FMS ")));
					}).map(map -> {
						log.info("unregister saved successfully");
						return map;
					}).then(Mono.just(ResponseEntity.status(HttpStatus.OK)
							.body("successfully participant unregister uploaded in FMS system ")));

		} catch (IOException e) {
			log.info("IOException in unregister uploaded  " + e.getMessage());
		}
		return Mono.just(ResponseEntity.status(HttpStatus.OK).body("successfully unregister uploaded ended"));
	}
}
