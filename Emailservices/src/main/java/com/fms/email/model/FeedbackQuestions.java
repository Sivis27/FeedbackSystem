package com.fms.email.model;

import java.util.Objects;

/**
 * FeedbackQuestions
 */
public class FeedbackQuestions {
	private String questions;

	private Integer totalAnswers;

	private String feedBackType;

	public FeedbackQuestions questions(String questions) {
		this.questions = questions;
		return this;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public FeedbackQuestions totalAnswers(Integer totalAnswers) {
		this.totalAnswers = totalAnswers;
		return this;
	}

	public Integer getTotalAnswers() {
		return totalAnswers;
	}

	public void setTotalAnswers(Integer totalAnswers) {
		this.totalAnswers = totalAnswers;
	}

	public FeedbackQuestions feedBackType(String feedBackType) {
		this.feedBackType = feedBackType;
		return this;
	}

	public String getFeedBackType() {
		return feedBackType;
	}

	public void setFeedBackType(String feedBackType) {
		this.feedBackType = feedBackType;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FeedbackQuestions feedbackQuestions = (FeedbackQuestions) o;
		return Objects.equals(this.questions, feedbackQuestions.questions)
				&& Objects.equals(this.totalAnswers, feedbackQuestions.totalAnswers)
				&& Objects.equals(this.feedBackType, feedbackQuestions.feedBackType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(questions, totalAnswers, feedBackType);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class FeedbackQuestions {\n");

		sb.append("    questions: ").append(toIndentedString(questions)).append("\n");
		sb.append("    totalAnswers: ").append(toIndentedString(totalAnswers)).append("\n");
		sb.append("    feedBackType: ").append(toIndentedString(feedBackType)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
