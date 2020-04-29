package com.fms.authenticate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fms.authenticate.entity.UserInfo;
import com.fms.authenticate.exception.UnauthorizedUserException;
import com.fms.authenticate.model.AuthRequest;
import com.fms.authenticate.model.AuthResponse;
import com.fms.authenticate.repository.AuthenticateService;
import com.fms.authenticate.repository.UserService;
import com.fms.authenticate.security.JWTUtil;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin
public class AuthenticateController {

	@Autowired
	JWTUtil jwtUtil;
	@Autowired
	AuthenticateService authenticateService;
	@Autowired
	UserService userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar) {
		log.info(" Login Authentication ");
		return userRepository.findByUsername(ar.getEmail())
				.switchIfEmpty(Mono.error(new UnauthorizedUserException(ar.getUsername())))
				.map((userDetails) -> {
					if (ar.getPassword().equals(userDetails.getUserpassword())) {
						return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));
					} else {
						return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
					}
				}).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PreAuthorize("hasRole('ADMIN')")
	public Mono<ServerResponse> getUserInfo(ServerRequest request) {
		return this.userRepository.findByUsername(Long.valueOf(request.pathVariable("id")))
				.flatMap(event -> ServerResponse.ok().body(Mono.just(event), UserInfo.class))
				.switchIfEmpty(ServerResponse.notFound().build());
	}

}
