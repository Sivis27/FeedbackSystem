package com.fms.authenticate.repository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fms.authenticate.entity.UserInfo;
import com.fms.authenticate.model.Role;

import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	UserAuthenticateRepository authenticateRepository;

	public Mono<UserInfo> findByUsername(String email) {
		Mono<UserInfo> userData = authenticateRepository.findUserByEmailId(email);
		userData = userData.flatMap(i -> {
			System.out.println("iiii===>" + i);
			i.setRoles(Arrays.asList(Role.getEnumNameForValue(i.getRolename())));
			System.out.println("iiii===>" + i);
			return Mono.just(i);
		});
		System.out.println(userData.map(i -> Arrays.asList(Role.getEnumNameForValue(i.getRolename()))));
		return userData;
	}

	public Mono<ResponseEntity<?>> findByUsername(Long valueOf) {
		// TODO Auto-generated method stub
		return null;
	}
}
