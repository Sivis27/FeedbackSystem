package com.fms.authenticate.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.authenticate.entity.UserInfo;

import reactor.core.publisher.Mono;

@Repository
public interface UserAuthenticateRepository extends ReactiveCrudRepository<UserInfo, Long> {
	@Query("select * from userinfo where emailid =:emailId")
	Mono<UserInfo> findUserByEmailId(String emailId);

}