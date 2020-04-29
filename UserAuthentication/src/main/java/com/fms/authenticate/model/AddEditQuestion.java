package com.fms.authenticate.model;

/**
 * AddQuestion
 */
public class AddEditQuestion {
	private String answerType;

	private String feedbackType;

	private String question;

	public AddEditQuestion(String answerType, String feedbackType, String question) {
		super();
		this.answerType = answerType;
		this.feedbackType = feedbackType;
		this.question = question;
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

}
