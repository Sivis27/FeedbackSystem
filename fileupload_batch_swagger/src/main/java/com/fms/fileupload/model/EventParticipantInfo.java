package com.fms.fileupload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("eventparticipantinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
}
