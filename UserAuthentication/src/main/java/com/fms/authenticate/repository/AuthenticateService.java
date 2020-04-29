package com.fms.authenticate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.authenticate.entity.UserInfo;

import reactor.core.publisher.Mono;

@Service
public class AuthenticateService {

	@Autowired
	UserAuthenticateRepository userRepository;

	public Mono<UserInfo> findUserByEmailId(String login) {
		return userRepository.findUserByEmailId(login);

	}

	

}
