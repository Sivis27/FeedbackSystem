package com.fms.events.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Table("feedbacktype")
public class FeedBacktype {

	@Id
	private Long id;
	private int typeid;
	private String typename;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public FeedBacktype(Long id, int typeid, String typename) {
		super();
		this.id = id;
		this.typeid = typeid;
		this.typename = typename;
	}

}
