package com.fms.events.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.events.handler.EventDetailsHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EventsRouter {
	@Bean
	public RouterFunction<ServerResponse> routes(EventDetailsHandler eventsHandler) {

		log.info("********** dashboard RouterFunction ****************  ");

		return RouterFunctions
				//DashBoardScreen
				.route(GET("/dashboard/{role}").and(accept(MediaType.APPLICATION_JSON)), eventsHandler::getDashboardDetails)
				//EventDetails Screen
				.andRoute(GET("/getAllEvents").and(accept(MediaType.APPLICATION_JSON)), eventsHandler::getAllEvents)
				//EventList Screen
				.andRoute(GET("/getEventsBy/{id}").and(accept(MediaType.APPLICATION_JSON)), eventsHandler::getEventById)
				//EventList by EventId Screen
				.andRoute(GET("/getEventsByEvent/{eventId}").and(accept(MediaType.APPLICATION_JSON)),
						eventsHandler::getEventByEventId).
				//EventDetails Screen
				andRoute(GET("/eventsInfo/{role}").and(accept(MediaType.APPLICATION_JSON)),
						eventsHandler::getAllEventByRoles).
				andRoute(GET("/userList/{role}").and(accept(MediaType.APPLICATION_JSON)),
								eventsHandler::getAllUsers).
				andRoute(POST("/assignUserRole").and(accept(MediaType.APPLICATION_JSON)),
						eventsHandler::assignUserRoles).
				andRoute(DELETE("/deleteUser/{id}").and(accept(MediaType.APPLICATION_JSON)),
						eventsHandler::removeAssignedRoles)
				;

	}

}