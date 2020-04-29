package com.fms.authenticate.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fms.authenticate.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table("userinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserInfo {
	@Id
	private Long id;
	private String rolename;
	private String firstname;
	private String lastname;
	private String userpassword;
	private String emailid;
	private String activeuser;
	private String employeeid;
	private List<Role> roles;

}
