package com.fms.email.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.email.handler.EmailHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EmailRouter {

	@Bean
	public RouterFunction<ServerResponse> route(EmailHandler emailHandler) {
		log.info("Reports routr called ");
		return RouterFunctions
				.route(RequestPredicates.GET("/getAllReports").and(accept(MediaType.APPLICATION_JSON)),
						emailHandler::getAllReports)
				.andRoute(GET("/generateReports").and(accept(MediaType.APPLICATION_JSON)),
						emailHandler::generateReports)
				.andRoute(POST("/downloadExcel/{excelType}").and(accept(MediaType.APPLICATION_JSON)),
						emailHandler::downloadExcel);
	}
}
