package com.fms.email.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("emailinfo")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	private List<String> toAddressList;
	
}
