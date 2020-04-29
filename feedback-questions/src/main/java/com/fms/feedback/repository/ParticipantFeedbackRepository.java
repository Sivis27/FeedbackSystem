package com.fms.feedback.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fms.feedback.document.ParticipantFeedback;

import reactor.core.publisher.Mono;


/**
 * The ParticipantFeedbackRepository is a simple interface which extend ReactiveMongoRepository to
 * provide all default methods to your entity/document repository.
 *
 */

@Repository
public interface ParticipantFeedbackRepository extends ReactiveMongoRepository<ParticipantFeedback, String> {

	Mono<ParticipantFeedback> findById(String questionId);
	
	Mono<ParticipantFeedback> findByquestionId(String questionId);
  
}
