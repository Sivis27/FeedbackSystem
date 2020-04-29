package com.fms.events.model;

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
public class DashboardInformation {

	private String event_id;
	private int lives_impacted;
	private int total_no_of_volunteers;
	private int total_no_of_Participants;


}
