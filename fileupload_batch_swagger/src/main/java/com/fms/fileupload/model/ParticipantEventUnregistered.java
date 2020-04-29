package com.fms.fileupload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("participantunregistered")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantEventUnregistered {
	@Id
	private Long id;
	private String event_id;
	private String eventname;
	private String beneficiaryname;
	private int employeeid;
	private String baselocation;
	private String eventdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getBeneficiaryname() {
		return beneficiaryname;
	}

	public void setBeneficiaryname(String beneficiaryname) {
		this.beneficiaryname = beneficiaryname;
	}

	public int getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getBaselocation() {
		return baselocation;
	}

	public void setBaselocation(String baselocation) {
		this.baselocation = baselocation;
	}

	public String getEventdate() {
		return eventdate;
	}

	public void setEventdate(String eventdate) {
		this.eventdate = eventdate;
	}

}