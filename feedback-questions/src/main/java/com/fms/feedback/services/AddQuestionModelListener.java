package com.fms.feedback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.fms.feedback.document.AddQuestion;

@Component
public class AddQuestionModelListener extends AbstractMongoEventListener<AddQuestion> {

	private SequenceGeneratorService sequenceGenerator;

	@Autowired
	public AddQuestionModelListener(SequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<AddQuestion> event) {
		if (event.getSource().getId()< 1) {
			event.getSource().setId(sequenceGenerator.generateSequence(AddQuestion.SEQUENCE_NAME));
		}
	}

}