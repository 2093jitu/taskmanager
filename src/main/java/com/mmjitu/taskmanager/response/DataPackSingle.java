package com.mmjitu.taskmanager.response;

import static com.mmjitu.taskmanager.utility.GlobalConstants.BAD_REQUEST;
import static com.mmjitu.taskmanager.utility.GlobalConstants.INTERNAL_ERROR;
import static com.mmjitu.taskmanager.utility.GlobalConstants.NO_CONTENT;
import static com.mmjitu.taskmanager.utility.GlobalConstants.OK;
import static com.mmjitu.taskmanager.utility.GlobalConstants.PRECONDITION_FAILED;
import static com.mmjitu.taskmanager.utility.GlobalConstants.UNAUTHORIZED;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataPackSingle<T> {

	private boolean result;
	private T model;
	private List<String> remarks;

	@JsonIgnore
	private String responseCode;

	@JsonIgnore
	private HttpStatusCode httpStatus;

	private String timestamp;

	@JsonIgnore
	private String correlationID;

	@JsonIgnore
	private String messageID;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public List<String> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public HttpStatusCode getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatusCode httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getCorrelationID() {
		return correlationID;
	}

	public void setCorrelationID(String correlationID) {
		this.correlationID = correlationID;
	}

	public String getMessageID() {
		return messageID;
	}

	public void setMessageID(String messageID) {
		this.messageID = messageID;
	}

	public DataPackSingle(boolean result, T model, List<String> remarks) {
		this.result = result;
		this.model = model;
		this.remarks = remarks;
	}

	public DataPackSingle(T data, List<String> remarks, String responseCode) {

		this.model = data;
		this.remarks = remarks;

		if (responseCode.equalsIgnoreCase(OK) && data != null) {
			this.result = true;
		} else {
			this.result = false;
		}
		this.httpStatus = getHttpStatus(responseCode);
		this.responseCode = responseCode;
		this.correlationID = "";
		this.messageID = "";
		this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
	}

	public DataPackSingle(T data, List<String> remarks, HttpStatusCode httpStatus) {

		this.model = data;
		this.remarks = remarks;

		if (httpStatus.value() == 200 && data != null) {
			this.result = true;
		} else {
			this.result = false;
		}
		this.httpStatus = httpStatus;
		this.responseCode = String.valueOf(httpStatus.value());
		this.correlationID = "";
		this.messageID = "";
		this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
	}

	private HttpStatus getHttpStatus(String httpStatus) {

		if (httpStatus.equals(PRECONDITION_FAILED)) {
			return HttpStatus.PRECONDITION_FAILED;

		} else if (httpStatus.equals(OK)) {
			return HttpStatus.OK;

		} else if (httpStatus.equals(INTERNAL_ERROR)) {
			return HttpStatus.INTERNAL_SERVER_ERROR;

		} else if (httpStatus.equals(NO_CONTENT)) {
			return HttpStatus.NO_CONTENT;

		} else if (httpStatus.equals(UNAUTHORIZED)) {
			return HttpStatus.UNAUTHORIZED;

		} else if (httpStatus.equals(BAD_REQUEST)) {
			return HttpStatus.BAD_REQUEST;

		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

}
