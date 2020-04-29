package com.fms.fileupload.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.fileupload.model.ParticipantNotAttended;

@Repository
public interface ParticipantNotAttendedRepository extends ReactiveCrudRepository<ParticipantNotAttended, Long> {

}