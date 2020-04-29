package com.fms.authenticate.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.fms.authenticate.entity.UserRole;

import reactor.core.publisher.Mono;

@Repository
public interface UserRolerepository extends ReactiveCrudRepository<UserRole, Long> {

	/*
	 * @Query("select *from userInfo where user_name=:username") Mono<UserRole>
	 * findByUsername(String username);
	 */

	@Query("select *from userrole where role_id=:roleId")
	Mono<UserRole> findUserByRoleId(String roleId);

}
