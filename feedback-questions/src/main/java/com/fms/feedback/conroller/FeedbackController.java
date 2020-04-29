package com.fms.feedback.conroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.feedback.document.AddQuestion;
import com.fms.feedback.document.ParticipantFeedback;
import com.fms.feedback.document.User;
import com.fms.feedback.repository.AddQuestionRepository;
import com.fms.feedback.repository.ParticipantFeedbackRepository;
import com.fms.feedback.repository.UserRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class FeedbackController {
	@Autowired
	AddQuestionRepository questionRepository;
	@Autowired
	ParticipantFeedbackRepository participantFeedbackRepository;

	@Autowired
	UserRepository userrepo;

	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	// Questions Home Page
	@GetMapping(value = "/loadQuestion")
	public Flux<AddQuestion> loadQuestion() {
		log.info(" MogoDB in loadQuestion ");
		return questionRepository.findAll();
	}

	@PostMapping(value = "/addQuestion")
	public Mono<AddQuestion> addQuestion(@RequestBody AddQuestion addQuestion) {
		log.info(" MogoDB  application called addQuestion");
		return questionRepository.save(addQuestion);
	}

	@PostMapping(value = "/adduser")
	public Mono<User> aduser(@RequestBody User dd) {
		log.info(" Event details application called addQuestion");
		return userrepo.save(dd);
	}

	@PutMapping(value = "/userupdate/{id}")
	public Mono<User> updatsue(@PathVariable("id") String id, @RequestBody User addQuestion) {
		Mono<User> monoQuestion = userrepo.findById(Long.valueOf(id));
		return monoQuestion.map(user -> {
			user.setFirstName(addQuestion.getFirstName());
			user.setLastName(addQuestion.getLastName());
			user.setEmail(addQuestion.getEmail());

			return user;
		}).flatMap(this.userrepo::save);
	}

	@PostMapping(value = "/participantFeedback")
	public Mono<ParticipantFeedback> questionAnswer(@RequestBody ParticipantFeedback participantFeedback) {
		log.info("MogoDB in participantFeedback  ");
		return participantFeedbackRepository.save(participantFeedback);
	}

	// Submit page
	@GetMapping(value = "/loadFBQA/{feedbackType}")
	public Flux<AddQuestion> loadFBQA(@PathVariable String feedbackType) {
		log.info(" Load FBQA called in MogoDB " + feedbackType);
		return questionRepository.findByfeedbackType(feedbackType).log();
	}

	@GetMapping(value = "/getQuestionById/{id}")
	public Mono<AddQuestion> getQuestionById(@PathVariable String id) {
		log.info(" MogoDB in loadQuestion " + id);
		return questionRepository.findById(Long.valueOf(id)).log();
	}

	/**
	 * Update an question by question ID and updated body coming in body. will be
	 * called by router to route incoming request at /updateaQuestion/{id} PUT and
	 * will return updated question record.
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */

	@PutMapping(value = "/updateaQuestion/{id}")
	public Mono<AddQuestion> updateaQuestion(@PathVariable("id") String id, @RequestBody AddQuestion addQuestion) {
		log.info(" MogoDB in updateaQuestion in controller  " + id);
		Mono<AddQuestion> monoQuestion = questionRepository.findById(Long.valueOf(id)).log();
		return monoQuestion.map(question -> {
			question.setFeedbackType(addQuestion.getFeedbackType());
			question.setQuestion(addQuestion.getQuestion());
			question.setQuestionAnswer(addQuestion.getQuestionAnswer());
			question.setQuestionType(addQuestion.getQuestionType());
			return question;
		}).flatMap(this.questionRepository::save);
	}

}
