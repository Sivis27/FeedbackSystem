package com.fms.fileUpload.services;

import org.springframework.batch.item.ItemProcessor;

import com.fms.fileUpload.document.EventSummary;

public class EventProcessor implements ItemProcessor<EventSummary, EventSummary> {

	@Override
	public EventSummary process(EventSummary item) throws Exception {
		return item;
	}

}