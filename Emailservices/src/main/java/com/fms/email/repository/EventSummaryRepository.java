package com.fms.email.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.email.entity.EventSummary;

import reactor.core.publisher.Flux;

@Repository
public interface EventSummaryRepository extends ReactiveCrudRepository<EventSummary, Long> {

	@Query("select  es.event_id ,es.event_month,es.baselocation, es.beneficiary_name, es.council_name , es.project, es.category,"
			+ " ep.employee_id,ep.employee_name,ep.businessunit,ep.event_status,ep.iiepcategory "
			+ "from eventsummary  es ,eventparticipantinfo ep  where es.event_id = ep.event_id ")
	Flux<EventSummary> generateReport();

	@Query("select * from eventsummary as e inner join userinfo as u on	u.employeeid = e.poc_id  where u.rolename = :role")
	Flux<EventSummary> getReportsByroles(String username);

}