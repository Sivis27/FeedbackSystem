package com.fms.authenticate.eventsclient;

import org.springframework.stereotype.Component;

@Component
public class UserHandler {
	/*
	 * @Autowired EventSummaryInfoRepo events;
	 * 
	 * public Mono<ServerResponse> getAllEvents(ServerRequest request) { return
	 * ok().contentType(MediaType.APPLICATION_JSON).body(events.findAll(),
	 * EventSummary.class); }
	 * 
	 * public Mono<ServerResponse> getEventById(ServerRequest request) { return
	 * this.events.findById(Long.parseLong(request.pathVariable("id")))
	 * .flatMap(event -> ServerResponse.ok().body(Mono.just(event),
	 * EventSummary.class)) .switchIfEmpty(ServerResponse.notFound().build()); }
	 * 
	 * public Mono<ServerResponse> getEventByEventId(ServerRequest request) { return
	 * this.events.getEventsByEventNo(request.pathVariable("eventId"))
	 * .flatMap(event -> ServerResponse.ok().body(Mono.just(event),
	 * EventSummary.class)) .switchIfEmpty(ServerResponse.notFound().build()); }
	 */

}
