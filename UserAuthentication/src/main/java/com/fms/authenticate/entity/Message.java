package com.fms.authenticate.entity;

import lombok.Data;
import lombok.ToString;
@Data  @ToString
public class Message {
	

	public Message(String content) {
		super();
		this.setContent(content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private String content;
	
}
