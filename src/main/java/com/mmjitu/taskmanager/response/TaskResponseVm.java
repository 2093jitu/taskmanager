package com.mmjitu.taskmanager.response;

import java.sql.Timestamp;
import com.mmjitu.taskmanager.entity.Task;

public class TaskResponseVm {

	private Long id;
	private String title;
	private String description;
	private Task.Status status;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Task.Status getStatus() {
		return status;
	}

	public void setStatus(Task.Status status) {
		this.status = status;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
