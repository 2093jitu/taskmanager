package com.mmjitu.taskmanager.config;

import java.sql.Timestamp;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class CustomExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	public static Map<String, Object> handleException(String classNameAndMethodName, String exceptionMessage){
		log.debug("------------------------------------>PROBLEM : [TIME] : "+ new Timestamp(System.currentTimeMillis())+" [From]-------------> : "+ classNameAndMethodName +"\n [Reason]-------------> : "+ exceptionMessage);
		return null;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
	    return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}