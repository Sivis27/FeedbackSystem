package com.fms.email.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("userinfo")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
	@Id
	private Long id;
	private String rolename;
	private String firstname;
	private String lastname;
	private String userpassword;
	private String emailid;
	private String employeeid;
	private boolean activeuser;

}
