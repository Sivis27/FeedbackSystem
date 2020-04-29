package com.fms.authenticate.model;

/**
 * GenerateReports
 */
public class GenerateReports {

	private String event_id;

	private String event_month;
	private String baselocation;

	private String beneficiary_name;

	private String council_name;

	private String project;

	private String category;

	private String event_date;

	private String employee_id;

	private String employee_name;

	private Integer volunteerHours;

	private Integer travelHours;

	private Integer livesImpacted;

	private String businessunit;

	private String event_status;

	private String iiepcategory;

	public GenerateReports() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenerateReports(String event_id, String event_month, String baselocation, String beneficiary_name,
			String council_name, String project, String category, String event_date, String employee_id,
			String employee_name, Integer volunteerHours, Integer travelHours, Integer livesImpacted,
			String businessunit, String event_status, String iiepcategory) {
		super();
		this.event_id = event_id;
		this.event_month = event_month;
		this.baselocation = baselocation;
		this.beneficiary_name = beneficiary_name;
		this.council_name = council_name;
		this.project = project;
		this.category = category;
		this.event_date = event_date;
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.volunteerHours = volunteerHours;
		this.travelHours = travelHours;
		this.livesImpacted = livesImpacted;
		this.businessunit = businessunit;
		this.event_status = event_status;
		this.iiepcategory = iiepcategory;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getEvent_month() {
		return event_month;
	}

	public void setEvent_month(String event_month) {
		this.event_month = event_month;
	}

	public String getBaselocation() {
		return baselocation;
	}

	public void setBaselocation(String baselocation) {
		this.baselocation = baselocation;
	}

	public String getBeneficiary_name() {
		return beneficiary_name;
	}

	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}

	public String getCouncil_name() {
		return council_name;
	}

	public void setCouncil_name(String council_name) {
		this.council_name = council_name;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public Integer getVolunteerHours() {
		return volunteerHours;
	}

	public void setVolunteerHours(Integer volunteerHours) {
		this.volunteerHours = volunteerHours;
	}

	public Integer getTravelHours() {
		return travelHours;
	}

	public void setTravelHours(Integer travelHours) {
		this.travelHours = travelHours;
	}

	public Integer getLivesImpacted() {
		return livesImpacted;
	}

	public void setLivesImpacted(Integer livesImpacted) {
		this.livesImpacted = livesImpacted;
	}

	public String getBusinessunit() {
		return businessunit;
	}

	public void setBusinessunit(String businessunit) {
		this.businessunit = businessunit;
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

}
