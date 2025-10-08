package com.mmjitu.taskmanager.config;

public class CustomSqlException extends RuntimeException {

	// --------- if we remove this CustomSqlException class shows warning, should study on that
	static final long serialVersionUID = -7034897190745766939L;

	public CustomSqlException(String msg) {
		super(msg);
	}

}