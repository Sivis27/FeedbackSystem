package com.fms.events.handler;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.events.entity.EventSummary;
import com.fms.events.entity.UserInfo;
import com.fms.events.exception.InvalidIdException;
import com.fms.events.exception.InvalidRequestException;
import com.fms.events.model.DashboardInformation;
import com.fms.events.repository.EventSummaryInfoRepo;
import com.fms.events.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@CrossOrigin
public class EventDetailsHandler {

	@Autowired
	EventSummaryInfoRepo eventSummaryRepo;
	@Autowired
	UserInfoRepository userInfoRepo;

	public Mono<ServerResponse> getAllEvents(ServerRequest request) {
		log.info(" getAllEvents called in Eventdetails ");
		return ok().contentType(MediaType.APPLICATION_JSON).body(eventSummaryRepo.findAll(), EventSummary.class);
	}

	public Mono<ServerResponse> getEventById(ServerRequest request) {
		log.info(" getEventById called in Eventdetails ");
		return this.eventSummaryRepo.findById(Long.parseLong(request.pathVariable("id")))
				.switchIfEmpty(Mono.error(new InvalidIdException()))
				.flatMap(event -> ServerResponse.ok().body(Mono.just(event), EventSummary.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> getEventByEventId(ServerRequest request) {
		log.info(" getEventByEventId called in Eventdetails ");
		return this.eventSummaryRepo.getEventsByEventNo(request.pathVariable("eventId"))
				.flatMap(event -> ServerResponse.ok().body(Mono.just(event), EventSummary.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> getDashboardDetails(ServerRequest request) {
		DashboardInformation dashboard = new DashboardInformation();
		String role = request.pathVariable("role");
		log.info(" ROLES BASED CALL  " + role);
		if (role.equals("admin")) {
			log.info(" getDashboardDetails called in Eventdetails admin " + role);
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
		} else {
			log.info(" getDashboardDetails called in Eventdetails for POC " + role);

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
	}

	public Mono<ServerResponse> getAllEventByRoles(ServerRequest request) {
		log.info(" getAllEventByRoles called in Eventdetails ");
		String role = request.pathVariable("role");
		if (role.equals("admin")) {
			return ok().contentType(MediaType.APPLICATION_JSON).body(eventSummaryRepo.findAll(), EventSummary.class);
		} else {
			return ok().contentType(MediaType.APPLICATION_JSON).body(this.eventSummaryRepo.getEventsByUser(role),
					EventSummary.class);
		}
	}

	
	
	public Mono<ServerResponse> getAllUsers(ServerRequest request) {
		log.info(" getAllEvents called in getAllUsers ");
		String role = request.pathVariable("role");
		return ok().contentType(MediaType.APPLICATION_JSON).body(userInfoRepo.findByrolename(role), UserInfo.class);
	}
	
	public Mono<ServerResponse> assignUserRoles(ServerRequest request) {
		return request.body(toMono(UserInfo.class)).flatMap(req -> {
			log.info(" Rolename to save " + req.getRolename());
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userInfoRepo.save(req),
					UserInfo.class);
		});
	}

	public Mono<ServerResponse> removeAssignedRoles(ServerRequest request) {
		log.info(" removeAssignedRoles called in Eventdetails ");
		return userInfoRepo.findById(Long.parseLong(request.pathVariable("id")))
				.switchIfEmpty(Mono.error(new InvalidRequestException()))
				.flatMap(user -> {
					log.info(" Rolename to be delete " + user.getRolename());
					return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userInfoRepo.delete(user),
							String.class);
				}).switchIfEmpty(ServerResponse.notFound().build());
	}

}
