package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table("fbqa")
public class FBQA {

	@Id
	private Long id;
	private int question_id;
	private int answer_id;
	private String answer;
	
	
	public FBQA(Long id, int question_id, int answer_id, String answer) {
		super();
		this.id = id;
		this.question_id = question_id;
		this.answer_id = answer_id;
		this.answer = answer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
