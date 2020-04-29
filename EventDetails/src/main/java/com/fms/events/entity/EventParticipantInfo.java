package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("eventparticipantinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipantInfo {

	@Id
	private Long id;
	private String event_id;
	private int employee_id;
	private String emailid;
	private String employee_name;
	private int volunteerhrs;
	private int travelhrs;
	private String businessunit;
	private int livesimpacted;
	private String event_status;
	private String iiepcategory;
	private boolean fbe_status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public int getVolunteerhrs() {
		return volunteerhrs;
	}

	public void setVolunteerhrs(int volunteerhrs) {
		this.volunteerhrs = volunteerhrs;
	}

	public int getTravelhrs() {
		return travelhrs;
	}

	public void setTravelhrs(int travelhrs) {
		this.travelhrs = travelhrs;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
	}

	public int getLivesimpacted() {
		return livesimpacted;
	}

	public void setLivesimpacted(int livesimpacted) {
		this.livesimpacted = livesimpacted;
	}

	public String getEvent_status() {
		return event_status;
	}

	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}

	public String getIiepcategory() {
		return iiepcategory;
	}

	public void setIiepcategory(String iiepcategory) {
		this.iiepcategory = iiepcategory;
	}

	public boolean isFbe_status() {
		return fbe_status;
	}

	public void setFbe_status(boolean fbe_status) {
		this.fbe_status = fbe_status;
	}

	@Override
	public String toString() {
		return "EventParticipantInfo [id=" + id + ", employee_id=" + employee_id + ", employee_name=" + employee_name
				+ ", volunteerhrs=" + volunteerhrs + ", travelhrs=" + travelhrs + ", businessunit=" + businessunit
				+ ", livesimpacted=" + livesimpacted + ", event_status=" + event_status + ", iiepcategory="
				+ iiepcategory + ", fbe_status=" + fbe_status + "]";
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

}
