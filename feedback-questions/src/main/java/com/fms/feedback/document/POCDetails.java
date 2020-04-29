package com.fms.feedback.document;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pocDetails")
public class POCDetails {

	@Id
	private String id;
	private String pocID;
	private String pocName;

	private String pocNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPocID() {
		return pocID;
	}

	public void setPocID(String pocID) {
		this.pocID = pocID;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getPocNumber() {
		return pocNumber;
	}

	public void setPocNumber(String pocNumber) {
		this.pocNumber = pocNumber;
	}

}
