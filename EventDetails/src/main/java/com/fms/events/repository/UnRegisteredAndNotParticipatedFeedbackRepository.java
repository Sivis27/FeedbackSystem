package com.fms.events.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.events.entity.UnRegisteredAndNotParticipatedFeedback;

@Repository
public interface UnRegisteredAndNotParticipatedFeedbackRepository
extends ReactiveCrudRepository<UnRegisteredAndNotParticipatedFeedback, Long> {

}