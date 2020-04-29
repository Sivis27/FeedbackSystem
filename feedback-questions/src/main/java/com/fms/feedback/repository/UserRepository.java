package com.fms.feedback.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fms.feedback.document.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, Long> {

	Mono<User> findById(Long id);

}