package com.fms.feedback.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.feedback.handler.QuestionHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 * QuestionsRouter is routing class to configure all your employee routes and
 * simple pass handler to handle incoming request for corresponding end points.
 *
 */

@Configuration
public class QuestionsRouter {

	@Bean
	public RouterFunction<ServerResponse> route(QuestionHandler fbHandler) {
		return RouterFunctions
				.route(GET("/question").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						fbHandler::getAllQuestion)
				.andRoute(GET("/question/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						fbHandler::getQuestion)
				.andRoute(POST("/question").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						fbHandler::addNewQuestion)
				.andRoute(PUT("/question/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						fbHandler::updateQuestion)
				.andRoute(POST("/addFeedback").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						fbHandler::addFeedback)
				.andRoute(GET("/loadFBQA/{feedbackType}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
						fbHandler::loadFBQA)

		;
	}
}
