package com.fms.feedback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.fms.feedback.document.ParticipantFeedback;

@Component
public class ParticipantFeedbackModelListener extends AbstractMongoEventListener<ParticipantFeedback> {

	private SequenceGeneratorService sequenceGenerator;

	@Autowired
	public ParticipantFeedbackModelListener(SequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<ParticipantFeedback> event) {
		if (event.getSource().getId() < 1) {
			event.getSource().setId(sequenceGenerator.generateSequence(ParticipantFeedback.SEQUENCE_NAME));
		}
	}

}