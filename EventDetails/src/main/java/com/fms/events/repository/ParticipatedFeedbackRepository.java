package com.fms.events.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.events.entity.ParticipatedFeedback;

@Repository
public interface ParticipatedFeedbackRepository extends  ReactiveCrudRepository<ParticipatedFeedback, Long> {

}