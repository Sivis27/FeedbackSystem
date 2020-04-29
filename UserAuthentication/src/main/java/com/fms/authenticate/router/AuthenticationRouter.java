package com.fms.authenticate.router;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.authenticate.controller.AuthenticateController;

@Configuration
public class AuthenticationRouter {
	@Bean
	public RouterFunction<ServerResponse> authenticate(AuthenticateController authenticationRouter) {
		return RouterFunctions.route(
				RequestPredicates.GET("/getEvents/{id}").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				authenticationRouter::getUserInfo);
	}
}
