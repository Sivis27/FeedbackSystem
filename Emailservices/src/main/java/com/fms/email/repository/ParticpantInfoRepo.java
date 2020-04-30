package com.fms.email.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.email.entity.EventParticipantInfo;
import com.fms.email.entity.EventSummary;

import reactor.core.publisher.Flux;

@Repository
public interface ParticpantInfoRepo extends ReactiveCrudRepository<EventParticipantInfo, Long> {

	@Query("select e.event_id ,e.employee_id,e.emailid from eventparticipantinfo e "
			+ " left join participantnotattended pnot"
			+ " on e.event_id = pnot.event_id and  e.employee_id = pnot.employeeid "
			+ "	left join participantunregistered unreg  "
			+ "	on e.event_id = unreg.event_id and  e.employee_id = unreg.employeeid "
			+ "	WHERE  pnot.event_id IS NULL AND unreg.event_id IS NULL")
	Flux<EventParticipantInfo> getEmailList();

}