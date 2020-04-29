package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("feedbackquestion")
public class FeedbackQuestion {
	@Id
	private Long id;
	private int feedbacktypeid;
	private int answertypeid;
	private String question;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getFeedbacktypeid() {
		return feedbacktypeid;
	}
	public void setFeedbacktypeid(int feedbacktypeid) {
		this.feedbacktypeid = feedbacktypeid;
	}
	public int getAnswertypeid() {
		return answertypeid;
	}
	public void setAnswertypeid(int answertypeid) {
		this.answertypeid = answertypeid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}
