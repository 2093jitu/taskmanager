package com.mmjitu.taskmanager.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

public class ResponseModel {

	private Map<String, Object> responseData;

	private ResponseEntity<Map<String, Object>> responseEntity = null;

	public ResponseModel() {
		this.responseData = new HashMap<String, Object>();
	}

	public Map<String, Object> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<String, Object> responseData) {
		this.responseData = responseData;
	}

	public ResponseEntity<Map<String, Object>> getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(ResponseEntity<Map<String, Object>> responseEntity) {
		this.responseEntity = responseEntity;
	}

}