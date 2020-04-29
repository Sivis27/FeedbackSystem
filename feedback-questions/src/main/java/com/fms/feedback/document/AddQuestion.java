package com.fms.feedback.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * AddQuestion
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Document(collection = "addquestion")
public class AddQuestion implements Serializable {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;
	private String question;
	private String feedbackType;
	private String questionType;
	private String[] questionAnswer;

}
