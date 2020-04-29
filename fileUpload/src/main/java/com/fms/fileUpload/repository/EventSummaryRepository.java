package com.fms.fileUpload.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.fms.fileUpload.document.EventSummary;

public interface EventSummaryRepository extends MongoRepository<EventSummary, String> {

}
