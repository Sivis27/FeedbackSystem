package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Table("questionanswer")
public class QuestionAnswer {

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Id
	private Long id;
	private int questionId;
	private String answer;
	
	


}
