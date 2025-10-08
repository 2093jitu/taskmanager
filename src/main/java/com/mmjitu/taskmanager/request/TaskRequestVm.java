package com.mmjitu.taskmanager.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.mmjitu.taskmanager.entity.Task;

public class TaskRequestVm {

	@NotBlank(message = "Title is mandatory")
	@Size(max = 255, message = "Title can be maximum 255 characters")
	private String title;

	private String description;

	private Task.Status status; // PENDING, IN_PROGRESS, COMPLETED

	// Getters & Setters
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
}
