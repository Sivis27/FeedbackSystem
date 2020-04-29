package com.fms.events.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.events.entity.AddQuestion;

import reactor.core.publisher.Mono;



@Repository
public interface AddAnswerQuestionRepository extends ReactiveCrudRepository<AddQuestion, Long> {
	@Query("select * from AddQuestion where questionid = :questionId ")
	Mono<AddQuestion> deleteQuestionById(String questionId);

	//Mono<AddQuestion> loadFBQA();

}