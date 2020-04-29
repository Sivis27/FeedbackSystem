package com.fms.authenticate.questionclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.fms.authenticate.constants.AuthenticateConstants;
import com.fms.authenticate.entity.AddQuestion;
import com.fms.authenticate.entity.EventSummary;
import com.fms.authenticate.entity.UserInfo;
import com.fms.authenticate.model.AddEditQuestion;
import com.fms.authenticate.model.ParticipatedFeedback;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class QuestionsWebClient {

	@Autowired
	WebClient.Builder clientBuilder;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/question")
	public Mono<AddQuestion[]> loadQuestions() {
		log.info(" question in QuestionsWebClient");
		return clientBuilder.build().get().uri(AuthenticateConstants.QUESTION_URL_MDB + "/question")
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AddQuestion[].class)
				.log("Get data  for dashboardInfor by roles ");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/question/{id}")
	public Mono<AddQuestion> getQuestion(@PathVariable String id) {
		log.info("question by id in QuestionsWebClient " + id);
		return clientBuilder.build().get().uri(AuthenticateConstants.QUESTION_URL_MDB + "/question/" + id)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AddQuestion.class)
				.log("Get data  for dashboardInfor by roles ");

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/question")
	public Mono<AddQuestion> addQuestion(@RequestBody AddQuestion question) {
		log.info(" Add a Question in QuestionsWebClient");
		return clientBuilder.build().post().uri(AuthenticateConstants.QUESTION_URL_MDB + "/question")
				.accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(question)).retrieve()
				.bodyToMono(AddQuestion.class);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/updateaQuestion/{id}")
	public Mono<AddQuestion> editQuestion(@PathVariable("id") String id, @RequestBody AddQuestion addQuestion) {
		log.info(" Update a Question in QuestionsWebClient");
		return clientBuilder.build().put().uri(AuthenticateConstants.QUESTION_URL_MDB + "/updateaQuestion/" + id)
				.accept(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(addQuestion)).retrieve()
				.bodyToMono(AddQuestion.class).log("Get data for editQuestion ");

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/addFeedback")
	public Mono<ParticipatedFeedback> addParticipantFeedback(@RequestBody ParticipatedFeedback participatedFeedback) {
		log.info(" Add a Feedback Question in QuestionsWebClient");
		return clientBuilder.build().post().uri(AuthenticateConstants.QUESTION_URL_MDB + "/addFeedback")
				.body(participatedFeedback, ParticipatedFeedback.class).retrieve()
				.bodyToMono(ParticipatedFeedback.class).log("Get data for addParticipantFeedback ");
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/loadFBQA/{feedbackType}")
	public Mono<AddQuestion[]> loadFBQA(@PathVariable String feedbackType) {
		log.info("loadFBQA in QuestionsWebClient " + feedbackType);
		return clientBuilder.build().get().uri(AuthenticateConstants.QUESTION_URL_MDB + "/loadFBQA/" + feedbackType)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AddQuestion[].class)
				.log("Get data  for dashboardInfor by roles ");

	}

}