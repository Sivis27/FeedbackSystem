package com.fms.email.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.email.model.UserInfo;


@Repository
public interface DownloadExcelRepository extends ReactiveCrudRepository<UserInfo, Long> {

}