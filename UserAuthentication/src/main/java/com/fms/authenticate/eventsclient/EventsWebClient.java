package com.fms.authenticate.eventsclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fms.authenticate.constants.AuthenticateConstants;
import com.fms.authenticate.entity.DashboardInformation;
import com.fms.authenticate.entity.EventSummary;
import com.fms.authenticate.entity.UserInfo;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class EventsWebClient {

	@Autowired
	WebClient.Builder clientBuilder;

	@PreAuthorize("hasRole('ADMIN') or hasRole('PMO')")
	@GetMapping("/dashboard/{role}")
	public Mono<DashboardInformation> dashboardRoles(@PathVariable String role) {
		log.info("********** dashboardRoles ****************  ");
		return clientBuilder.build().get().uri(AuthenticateConstants.EVENTS_URL + "/dashboard/" + role)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(DashboardInformation.class)
				.log("Get data  for dashboardInfor by roles ");

	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('PMO')")
	@GetMapping("/eventsInfo/{role}")
	public Mono<EventSummary[]> eventsInfoRoles(@PathVariable String role) {
		log.info("********** eventsInfoRoles ****************  ");
		return clientBuilder.build().get().uri(AuthenticateConstants.EVENTS_URL + "/eventsInfo/" + role)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EventSummary[].class)
				.log("Get data  for eventsInfoRoles by roles ");

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllEvents")
	public Mono<EventSummary[]> allevents() {
		log.info("********** findById ****************  ");
		return clientBuilder.build().get().uri(AuthenticateConstants.EVENTS_URL + "/getAllEvents")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EventSummary[].class).log();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/alleventsByid/{id}")
	public Mono<EventSummary> alleventsByid(@PathVariable String id) {
		log.info("********** alleventsByid ****************  " + id);
		return clientBuilder.build().get().uri(AuthenticateConstants.EVENTS_URL + "/getEventsBy/" + id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(EventSummary.class).log();
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUsers/{role}")
	public Mono<UserInfo[]> allUsers(@PathVariable String role) {
		log.info("********** findById ****************  ");
		return clientBuilder.build().get().uri(AuthenticateConstants.EVENTS_URL + "/userList/"+role)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(UserInfo[].class).log();
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/assignRoles")
	public Mono<UserInfo> assignUserRoles(@RequestBody UserInfo userInfo) {
		log.info("********** Assign Users ****************  ");
		return clientBuilder.build().post().uri(AuthenticateConstants.EVENTS_URL + "/assignUserRole")
				.body(BodyInserters.fromObject(userInfo)).retrieve().bodyToMono(UserInfo.class);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/removeRoles/{id}")
	public Mono<String> delete(@PathVariable String id) {
		log.info("********** removeRoles ****************  " + id);
		return clientBuilder.build().delete().uri(AuthenticateConstants.EVENTS_URL + "/deleteUser/" + id).retrieve()
				.bodyToMono(String.class).log("Delete data for UserRoles");
	}

}