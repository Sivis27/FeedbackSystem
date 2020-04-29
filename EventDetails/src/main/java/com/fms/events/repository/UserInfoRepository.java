package com.fms.events.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.events.entity.UserInfo;

import reactor.core.publisher.Mono;

@Repository
public interface UserInfoRepository extends ReactiveCrudRepository<UserInfo, Long> {

	
	@Query("select *from UserInfo where user_name=:rolename")
	Mono<UserInfo> findByrolename(String username);

}