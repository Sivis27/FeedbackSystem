package com.fms.events.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.events.entity.EventSummary;
import com.fms.events.model.DashboardInformation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventSummaryInfoRepo extends ReactiveCrudRepository<EventSummary, Long> {

	@Query("select * from eventsummary where event_id = :eventId ")
	Mono<EventSummary> getEventsByEventNo(String eventId);

	@Query("select count(event_id) as totalEvents, sum(lives_impacted) as livesImpacted, sum(total_no_of_volunteers) as totalVolunteers, sum(total_no_of_volunteers) as totalParticipants from eventsummary")
	Mono<DashboardInformation> getDashboardDetails();

	@Query("select * from eventsummary as e inner join userinfo as u on	 u.employeeid = e.poc_id  where u.rolename = :role")
	Flux<EventSummary> getEventsByUser(String role);

}