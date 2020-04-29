package com.fms.authenticate.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("eventsummary")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventSummary {
	@Id
	private Long id;
	private String event_id;
	private String event_month;
	private String baselocation;
	private String beneficiary_name;
	private String venue_address;
	private String council_name;
	private String project;
	private String category;
	private String eventname;
	private String event_description;
	private String event_date;
	private int total_no_of_volunteers;
	private int total_no_of_volunteers_hrs;
	private int total_travel_hrs;
	private int overall_volunteer_hrs;
	private int lives_impacted;
	private int activity_type;
	private String event_status;
	private int poc_id;
	private String poc_name;
	private int poc_number;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getBase_location() {
		return baselocation;
	}

	public void setBase_location(String base_location) {
		this.baselocation = base_location;
	}

	public String getBeneficiary_name() {
		return beneficiary_name;
	}

	public void setBeneficiary_name(String beneficiary_name) {
		this.beneficiary_name = beneficiary_name;
	}

	public String getVenue_address() {
		return venue_address;
	}

	public void setVenue_address(String venue_address) {
		this.venue_address = venue_address;
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

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public int getTotal_no_of_volunteers() {
		return total_no_of_volunteers;
	}

	public void setTotal_no_of_volunteers(int total_no_of_volunteers) {
		this.total_no_of_volunteers = total_no_of_volunteers;
	}

	public int getTotal_no_of_volunteers_hrs() {
		return total_no_of_volunteers_hrs;
	}

	public void setTotal_no_of_volunteers_hrs(int total_no_of_volunteers_hrs) {
		this.total_no_of_volunteers_hrs = total_no_of_volunteers_hrs;
	}

	public int getTotal_travel_hrs() {
		return total_travel_hrs;
	}

	public void setTotal_travel_hrs(int total_travel_hrs) {
		this.total_travel_hrs = total_travel_hrs;
	}

	public int getOverall_volunteer_hrs() {
		return overall_volunteer_hrs;
	}

	public void setOverall_volunteer_hrs(int overall_volunteer_hrs) {
		this.overall_volunteer_hrs = overall_volunteer_hrs;
	}

	public int getLives_impacted() {
		return lives_impacted;
	}

	public void setLives_impacted(int lives_impacted) {
		this.lives_impacted = lives_impacted;
	}

	public int getActivity_type() {
		return activity_type;
	}

	public void setActivity_type(int activity_type) {
		this.activity_type = activity_type;
	}

	public String getEvent_status() {
		return event_status;
	}

	public void setEvent_status(String event_status) {
		this.event_status = event_status;
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

	@Override
	public String toString() {
		return "EventSummary [id=" + id + ", event_id=" + event_id + ", event_month=" + event_month + ", base_location="
				+ baselocation + ", beneficiary_name=" + beneficiary_name + ", venue_address=" + venue_address
				+ ", council_name=" + council_name + ", project=" + project + ", category=" + category + ", eventname="
				+ eventname + ", event_description=" + event_description + ", event_date=" + event_date
				+ ", total_no_of_volunteers=" + total_no_of_volunteers + ", total_no_of_volunteers_hrs="
				+ total_no_of_volunteers_hrs + ", total_travel_hrs=" + total_travel_hrs + ", overall_volunteer_hrs="
				+ overall_volunteer_hrs + ", lives_impacted=" + lives_impacted + ", activity_type=" + activity_type
				+ ", event_status=" + event_status + ", poc_id=" + poc_id + ", poc_name=" + poc_name + ", poc_number="
				+ poc_number + "]";
	}

}
