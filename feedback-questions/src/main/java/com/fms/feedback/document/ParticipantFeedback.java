package com.fms.feedback.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * AddQuestion
 */

@Document(collection = "participantfeedback")
public class ParticipantFeedback implements Serializable {
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;
	private String participantId;
	private String eventId;
	private String questionId;
	private String answer;
	private String questionType;

	public ParticipantFeedback() {
	}

	public ParticipantFeedback(String questionId, String answer) {
		this.questionId = questionId;
		this.answer = answer;
	}

	public ParticipantFeedback(String participantId, String eventId, String questionId, String answer,
			String questionType) {
		this.participantId = participantId;
		this.eventId = eventId;
		this.questionId = questionId;
		this.answer = answer;
		this.questionType = questionType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return "ParticipantFeedback [id=" + id + ", participantId=" + participantId + ", eventId=" + eventId
				+ ", questionId=" + questionId + ", answer=" + answer + ", questionType=" + questionType + "]";
	}

}
