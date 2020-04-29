package com.fms.email.model;

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
public class BulkEmail {
	private String event_id;
	private String employee_id;
	private String emailid;
}
