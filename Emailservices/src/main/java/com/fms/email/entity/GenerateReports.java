package com.fms.email.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

}
