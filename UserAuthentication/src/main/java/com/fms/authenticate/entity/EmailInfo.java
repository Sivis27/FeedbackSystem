package com.fms.authenticate.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("emailinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailInfo {
	@Id
	private Long id;
	private String eventid;
	private String eventname;
	private String eventlocation;
	private String eventdate;
	private String employeeid;
	private Date sendon;
	private String eventsubject;
	private String fromaddress;
	private String toaddress;
	private String eventbody;
	private String emailstatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventlocation() {
		return eventlocation;
	}
	public void setEventlocation(String eventlocation) {
		this.eventlocation = eventlocation;
	}
	public String getEventdate() {
		return eventdate;
	}
	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public Date getSendon() {
		return sendon;
	}
	public void setSendon(Date sendon) {
		this.sendon = sendon;
	}
	public String getEventsubject() {
		return eventsubject;
	}
	public void setEventsubject(String eventsubject) {
		this.eventsubject = eventsubject;
	}
	public String getFromaddress() {
		return fromaddress;
	}
	public void setFromaddress(String fromaddress) {
		this.fromaddress = fromaddress;
	}
	public String getToaddress() {
		return toaddress;
	}
	public void setToaddress(String toaddress) {
		this.toaddress = toaddress;
	}
	public String getEventbody() {
		return eventbody;
	}
	public void setEventbody(String eventbody) {
		this.eventbody = eventbody;
	}
	public String getEmailstatus() {
		return emailstatus;
	}
	public void setEmailstatus(String emailstatus) {
		this.emailstatus = emailstatus;
	}
	
}
