package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * AddAnswer
 */

@Table("addquestion")
public class AddQuestion {
	@Id
	private Long id;
	private String question;
	private String feedbackType;
	private String questionType;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	@Override
	public String toString() {
		return "AddQuestion [id=" + id + ", question=" + question + ", feedbackType=" + feedbackType + ", questionType="
				+ questionType + "]";
	}
	
}
