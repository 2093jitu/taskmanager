package com.mmjitu.taskmanager.utility;

import static com.mmjitu.taskmanager.utility.GlobalConstants.INTERNAL_ERROR;
import static com.mmjitu.taskmanager.utility.GlobalConstants.NO_CONTENT;
import static com.mmjitu.taskmanager.utility.GlobalConstants.OK;
import static com.mmjitu.taskmanager.utility.GlobalConstants.PRECONDITION_FAILED;
import static com.mmjitu.taskmanager.utility.GlobalConstants.RESPONSE_CODE;
import static com.mmjitu.taskmanager.utility.GlobalConstants.UNAUTHORIZED;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mmjitu.taskmanager.response.ResponseModel;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CommonControllerUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CommonControllerUtil.class);
	
	public ResponseEntity<Map<String, Object>> getResponseEntity(Map<String, Object> data, HttpServletRequest request) {
		
		ResponseModel responseModel = new ResponseModel();
		responseModel.setResponseData(data);
		
		if (data.get(RESPONSE_CODE).equals(PRECONDITION_FAILED)) {			
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.PRECONDITION_FAILED));		
		} else if (data.get(RESPONSE_CODE).equals(OK)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK));
		} else if (data.get(RESPONSE_CODE).equals(INTERNAL_ERROR)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.INTERNAL_SERVER_ERROR));
		} else if (data.get(RESPONSE_CODE).equals(NO_CONTENT)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.NO_CONTENT));
		} else if (data.get(RESPONSE_CODE).equals(UNAUTHORIZED)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.UNAUTHORIZED));
		}else {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.NOT_FOUND));
		}
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String requestId = (String) request.getAttribute("requestId");
		//log.debug("\n\nrequestId : {}, , RESPONSE_BODY ------>  {}\n", requestId , gson.toJson(data));
		
		return responseModel.getResponseEntity();
	}
	
	public ResponseEntity<Map<String, Object>> getResponseEntity(Map<String, Object> data) {

		ResponseModel responseModel = new ResponseModel();
		responseModel.setResponseData(data);
		
		if (data.get(RESPONSE_CODE).equals(PRECONDITION_FAILED)) {			
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.PRECONDITION_FAILED));		
		} else if (data.get(RESPONSE_CODE).equals(OK)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK));
		} else if (data.get(RESPONSE_CODE).equals(INTERNAL_ERROR)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.INTERNAL_SERVER_ERROR));
		} else if (data.get(RESPONSE_CODE).equals(NO_CONTENT)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.NO_CONTENT));
		} else if (data.get(RESPONSE_CODE).equals(UNAUTHORIZED)) {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.UNAUTHORIZED));
		}else {
			responseModel.setResponseEntity(new ResponseEntity<Map<String, Object>>(data, HttpStatus.NOT_FOUND));
		}
		
		Gson gson = new Gson();
		log.debug("\n\n RESPONSE_BODY ------>  {}\n", gson.toJson(data));
		
		return responseModel.getResponseEntity();
	}
	
	
	
}
