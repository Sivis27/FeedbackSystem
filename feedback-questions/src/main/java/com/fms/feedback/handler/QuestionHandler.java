package com.fms.feedback.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.feedback.document.AddQuestion;
import com.fms.feedback.document.Employee;
import com.fms.feedback.document.ParticipantFeedback;
import com.fms.feedback.exception.InvalidIdException;
import com.fms.feedback.repository.AddQuestionRepository;
import com.fms.feedback.repository.EmployeeRepository;
import com.fms.feedback.repository.ParticipantFeedbackRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@CrossOrigin
public class QuestionHandler {

	@Autowired
	private AddQuestionRepository questionRepository;
	@Autowired
	ParticipantFeedbackRepository participantFeedbackRepository;

	/**
	 * Get All Questions method will be called by router to route incoming request
	 * at /question GET and will return all questions available in DB.
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */

	public Mono<ServerResponse> getAllQuestion(ServerRequest request) {
		log.info(" MogoDB in getAllQuestion ");
		Flux<AddQuestion> employees = questionRepository.findAll();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(employees, AddQuestion.class);
	}

	/**
	 * Get an Question method will be called by router to route incoming request at
	 * /question/{id} GET and will return an Question based on question ID
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */
	public Mono<ServerResponse> getQuestion(ServerRequest request) {
		String questionId = request.pathVariable("id");
		log.info(" MogoDB in getQuestion " + questionId);
		return questionRepository.findById(Long.valueOf(questionId))
				.flatMap(question -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
						.body(Mono.just(question), AddQuestion.class))
				.switchIfEmpty(ServerResponse.notFound().build()).log();
	}

	/**
	 * Add a new question method will be called by router to route incoming request
	 * at /question POST and will return created questions.
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */
	public Mono<ServerResponse> addNewQuestion(ServerRequest request) {
		log.info(" MogoDB in addNewQuestion ");
		Mono<AddQuestion> addQuestion = request.bodyToMono(AddQuestion.class);
		Mono<AddQuestion> question = addQuestion.flatMap(questionRepository::save);
		return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(question,
				AddQuestion.class);
	}

	/**
	 * Update an question by question ID and updated body coming in body. will be
	 * called by router to route incoming request at /question/{id} PUT and will
	 * return updated question record.
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */

	public Mono<ServerResponse> updateQuestion(ServerRequest request) {

		String qId = request.pathVariable("id");
		log.info(" MogoDB in updateQuestion id - " + qId);
		Mono<AddQuestion> questionMono = request.bodyToMono(AddQuestion.class).log();
		return questionRepository.findById(Long.valueOf(qId)).flatMap(question -> questionMono.flatMap(ques -> {
			question.setFeedbackType(ques.getFeedbackType());
			question.setQuestion(ques.getQuestion());
			Mono<AddQuestion> updatedQuestion = questionRepository.save(question);
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(updatedQuestion, AddQuestion.class);
		}));
	}

	/**
	 * Add a new Feedback method will be called by router to route incoming request
	 * at /addFeedack POST and will return a created feedback questions.
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */

	public Mono<ServerResponse> addFeedback(ServerRequest request) {
		log.info(" MogoDB in addFeedback  ");
		Mono<ParticipantFeedback> addQuestion = request.bodyToMono(ParticipantFeedback.class);
		Mono<ParticipantFeedback> question = addQuestion.flatMap(participantFeedbackRepository::save);
		return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(question,
				ParticipantFeedback.class);
	}

	/**
	 * Get All feedback questions method will be called by router to route incoming
	 * request at /loadFBQA/{feedbackType} GET and will return all feedback
	 * questions available in DB.
	 * 
	 * @param request //incoming server request
	 * @return response //outgoing server response.
	 */
	public Mono<ServerResponse> loadFBQA(ServerRequest request) {
		String fbType = request.pathVariable("feedbackType");
		log.info(" Load FBQA called in MogoDB " + fbType);
		return ok().contentType(MediaType.APPLICATION_JSON).body(
				this.questionRepository.findByfeedbackType(fbType).switchIfEmpty(Mono.error(new InvalidIdException())),
				AddQuestion.class);

	}
}
