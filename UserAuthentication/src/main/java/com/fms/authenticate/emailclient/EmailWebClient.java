package com.fms.authenticate.emailclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fms.authenticate.constants.AuthenticateConstants;
import com.fms.authenticate.entity.EventSummary;
import com.fms.authenticate.model.AuthRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class EmailWebClient {

	@Autowired
	WebClient.Builder clientBuilder;

	@PreAuthorize("hasRole('ADMIN') ")
	@PostMapping("/mailForAdmin")
	public Mono<String> triggerEmailForAdmin(@RequestBody AuthRequest ar) {
		return clientBuilder.build().post().uri(AuthenticateConstants.EMAIL_URL + "/mailForAdmin")
				.body(ar, String.class).retrieve().bodyToMono(String.class).log("Get data for triggerEmailForAdmin ");

	}

	@PostMapping("/mailForPMO")
	public Mono<String> triggerEmailForPMO() {
		log.info(" Inside mail for PMO mial services ");
		WebClient.builder().build().get().uri(AuthenticateConstants.EMAIL_URL + "/mailForPMO").retrieve()
				.bodyToMono(String.class).log("get UserInfo data");
		return Mono.just("Notifcation mail sent succesfully for PMO Role");

	}

	@PreAuthorize("hasRole('PARTICIPANT') ")
	@PostMapping("/mailForPOC")
	public Mono<String> triggerEmailForPOC() {
		log.info(" Authnetican call for POC Email Services ");
		return clientBuilder.build().post().uri(AuthenticateConstants.EMAIL_URL + "/mailForPOC").retrieve()
				.bodyToMono(String.class).log("post data");

	}

	@GetMapping(value = "/eventsParticipants/{id}")
	public Mono<EventSummary> getEventsById(@PathVariable String id) {
		log.info("Inside Emailservies call " + id);
		return clientBuilder.build().get().uri(AuthenticateConstants.EMAIL_URL + "/events/{id}", id).retrieve()
				.bodyToMono(EventSummary.class).log("post data");

	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/downloadExcel/{excelType}")
	public Mono<EventSummary> eventsInfoRoles(@PathVariable String excelType) {
		log.info("********** eventsInfoRoles ****************  ");
		return clientBuilder.build().post().uri(AuthenticateConstants.EMAIL_URL + "/downloadExcel/" + excelType)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EventSummary.class)
				.log("Get data  for eventsInfoRoles by roles ");

	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/generateReport")
	public Mono<EventSummary[]> generateReport() {
		log.info("* generateReport in EmailClient *");
		return clientBuilder.build().get().uri(AuthenticateConstants.EMAIL_URL + "/getAllReports")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EventSummary[].class).log();
	}

	
	
}
