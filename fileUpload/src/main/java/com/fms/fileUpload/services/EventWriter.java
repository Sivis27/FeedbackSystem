package com.fms.fileUpload.services;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fms.fileUpload.document.EventSummary;
import com.fms.fileUpload.repository.EventSummaryRepository;

public class EventWriter implements ItemWriter<EventSummary> {

	@Autowired
	EventSummaryRepository eventRepo;

	@Override
	public void write(List<? extends EventSummary> items) throws Exception {
		eventRepo.saveAll(items);
	}

}