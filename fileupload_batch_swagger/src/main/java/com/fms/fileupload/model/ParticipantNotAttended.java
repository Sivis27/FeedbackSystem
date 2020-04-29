package com.fms.fileupload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("participantnotattended")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantNotAttended {
	@Id
	private Long id;
	private String event_id;
	private String eventname;
	private String beneficiaryname;
	private int employeeid;
	private String baselocation;
	private String eventdate;

	
}
