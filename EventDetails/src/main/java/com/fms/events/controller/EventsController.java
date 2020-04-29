package com.fms.events.controller;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.events.entity.EventSummary;
import com.fms.events.entity.UserInfo;
import com.fms.events.model.DashboardInformation;
import com.fms.events.repository.AddAnswerQuestionRepository;
import com.fms.events.repository.AddFeedbackQuestionRepository;
import com.fms.events.repository.EventSummaryInfoRepo;
import com.fms.events.repository.ParticipatedFeedbackRepository;
import com.fms.events.repository.UnRegisteredAndNotParticipatedFeedbackRepository;
import com.fms.events.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin

public class EventsController {

	@Autowired
	EventSummaryInfoRepo eventSummaryRepo;
	@Autowired
	private DatabaseClient databaseClient;
	@Autowired
	AddFeedbackQuestionRepository addFeedbackQuestionRepository;
	@Autowired
	ParticipatedFeedbackRepository participatedFeedbackRepository;
	@Autowired
	UnRegisteredAndNotParticipatedFeedbackRepository unRegisteredAndNotParticipatedFeedbackRepository;
	@Autowired
	AddAnswerQuestionRepository questionRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@GetMapping("/dashboardInfoAdmin")
	public Mono<ResponseEntity<DashboardInformation>> dashboardSummaryForAdminPMO() {

		return databaseClient
				.execute("Select count(event_id) as event_id,\n" + "  sum(lives_impacted) as lives_impacted , \n"
						+ "  sum(total_no_of_volunteers) as total_no_of_volunteers , \n"
						+ "  sum(total_no_of_volunteers)  as total_no_of_Participants \n" + "  FROM eventsummary;")
				.as(DashboardInformation.class).fetch().first().map(dashboard ->

				ResponseEntity.ok(dashboard)).defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();
	}

	@GetMapping("/dashboardInfoAdminMono")
	public Mono<ServerResponse> getDashboardDsetails() {
		DashboardInformation dashboard = new DashboardInformation();
		return this.eventSummaryRepo.findAll().count().map(event -> {
			dashboard.setEvent_id(String.valueOf(event));
			return event;
		}).and(this.eventSummaryRepo.findAll().map(mapper -> mapper.getLives_impacted()).reduce(0, (c, e) -> c + e)
				.map(mapper -> {
					dashboard.setLives_impacted(mapper);
					return mapper;
				})).and((this.eventSummaryRepo.findAll().map(mapper -> mapper.getTotal_no_of_volunteers())
						.reduce(0, (c, e) -> c + e).map(voluteers -> {
							dashboard.setTotal_no_of_volunteers(voluteers);
							dashboard.setTotal_no_of_Participants(voluteers);
							return voluteers;
						})))
				.then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(dashboard),
						DashboardInformation.class));
	}

	@GetMapping("/dashboardInfoPOC")
	public Mono<ResponseEntity<DashboardInformation>> dashboardSummaryForAdminPOC() {

		return databaseClient.execute(
				"Select count(event_id) as event_id,sum(lives_impacted) lives_impacted , sum(total_no_of_volunteers) as total_no_of_volunteers , sum(total_no_of_volunteers) as total_no_of_Participants FROM eventsummary")
				.as(DashboardInformation.class).fetch().first().map(dashboard -> ResponseEntity.ok(dashboard))
				.defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).log();

	}

	@GetMapping(value = "/eventsInfoAdminPMO/{user}")
	public Mono<ServerResponse> getAllEvent(ServerRequest request) {
		String role = request.pathVariable("user");
		if (role.equals("admin")) {
			return ok().contentType(MediaType.APPLICATION_JSON).body(eventSummaryRepo.findAll(), EventSummary.class);
		} else {
			return ok().contentType(MediaType.APPLICATION_JSON).body(this.eventSummaryRepo.getEventsByUser(role),
					EventSummary.class);
		}
	}
	
	@GetMapping(value = "/eventsInfoAdminPMOtt")
	public Flux<EventSummary> getAllEventsAdminPMO() {
		log.info(" Event details application called getAllEvents");
		return eventSummaryRepo.findAll();
	}

	@GetMapping(value = "/eventsInfoAdminPOC/{id}")
	public Mono<EventSummary> getEventsById(@PathVariable String id) {
		log.info(" Event details application called  - getEventsById");
		System.out.println(" inside Authentcationfunction " + id);
		return eventSummaryRepo.getEventsByEventNo(id);
	}

	@PostMapping(value = "/assignRoles")
	public Mono<UserInfo> assignUserRoles(@RequestBody UserInfo userInfo) {
		return userInfoRepository.save(userInfo).log();
	}

	@DeleteMapping(value = "/removeAssignedRoles/{id}")
	public Mono removeAssignedRoles(@PathVariable String id) {
		Mono<UserInfo> answerById = userInfoRepository.findById(Long.parseLong(id));
		if (Objects.isNull(answerById)) {
			return Mono.empty();
		}
		return userInfoRepository.findById(Long.valueOf(id)).switchIfEmpty(Mono.empty()).filter(Objects::nonNull)
				.flatMap(idToBeDeleted -> userInfoRepository.delete(idToBeDeleted).then(Mono.just(idToBeDeleted)));
	}
}