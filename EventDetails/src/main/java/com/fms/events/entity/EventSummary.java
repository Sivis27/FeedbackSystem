package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("eventsummary")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

}
