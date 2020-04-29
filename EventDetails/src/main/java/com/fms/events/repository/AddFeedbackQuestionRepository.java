package com.fms.events.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.events.entity.FeedbackQuestion;


@Repository
public interface AddFeedbackQuestionRepository extends ReactiveCrudRepository<FeedbackQuestion, Long> {

}