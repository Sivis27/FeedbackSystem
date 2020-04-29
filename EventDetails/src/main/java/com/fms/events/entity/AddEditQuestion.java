package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * AddQuestion
 */

@Table("addeditquestion")
public class AddEditQuestion {
	@Id
	private Long id;
	private String answerType;

	private String feedbackType;

	private String question;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "AddEditQuestion [id=" + id + ", answerType=" + answerType + ", feedbackType=" + feedbackType
				+ ", question=" + question + "]";
	}


}
