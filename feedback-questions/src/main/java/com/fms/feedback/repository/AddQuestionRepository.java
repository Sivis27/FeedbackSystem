package com.fms.feedback.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.fms.feedback.document.AddQuestion;

import reactor.core.publisher.Flux;

/**
 * The AddQuestionRepository is a simple interface which extend ReactiveMongoRepository to
 * provide all default methods to your entity/document repository.
 *
 */
@Repository
public interface AddQuestionRepository extends ReactiveMongoRepository<AddQuestion, Long> {

	//Mono<AddQuestion> findByid(String questionId);

	Flux<AddQuestion> findByfeedbackType(String questionType);
	
	Flux<AddQuestion>  countByquestionAnswer(String category);
	
}
