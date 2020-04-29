package com.fms.email.repository;

import java.util.List;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.email.entity.EmailInfo;

@Repository
public interface EmailRepository extends ReactiveCrudRepository<EmailInfo, Long> {

	@Query("SELECT * FROM userinfo WHERE  is_active_user=1 ")
	public List<EmailInfo> findAllEmps();

}