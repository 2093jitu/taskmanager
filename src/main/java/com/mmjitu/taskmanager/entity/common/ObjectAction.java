package com.mmjitu.taskmanager.entity.common;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class ObjectAction {

//	@Column(name = "created_by")
//	private Long createdBy;

	@Column(name = "created_at")
	private Timestamp createdAt;

//	@Column(name = "modified_by")
//	private Long modifiedBy;

	@Column(name = "modified_at")
	private Timestamp modifiedAt;

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}