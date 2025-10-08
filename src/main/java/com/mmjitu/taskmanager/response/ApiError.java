package com.mmjitu.taskmanager.response;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private String errors;
	private List<String> message;

	public ApiError(HttpStatus status, String errors, List<String> message) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ApiError(HttpStatus status, String errors, String message) {
		this.status = status;
		this.errors = errors;
		this.message = Arrays.asList(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}
}