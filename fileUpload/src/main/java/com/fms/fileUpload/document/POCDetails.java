package com.fms.fileUpload.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "pocDetails")
public class POCDetails {

	@Id
	private int id;
	
	private int poc_id;
	private String poc_name;
	private int poc_number;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoc_id() {
		return poc_id;
	}
	public void setPoc_id(int poc_id) {
		this.poc_id = poc_id;
	}
	public String getPoc_name() {
		return poc_name;
	}
	public void setPoc_name(String poc_name) {
		this.poc_name = poc_name;
	}
	public int getPoc_number() {
		return poc_number;
	}
	public void setPoc_number(int poc_number) {
		this.poc_number = poc_number;
	}

}
