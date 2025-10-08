package com.mmjitu.taskmanager.utility;

public class GlobalConstants {

	// Key
	public static final String DATA = "data";
	public static final String MODEL = "model";
	public static final String MESSAGE = "message";
	public static final String RESPONSE_CODE = "responseCode";
	public static final String DATA_SOURCE_CORE = "CORE";
	public static final String DATA_SOURCE_MIDDLEWARE = "MIDDLEWARE";

	public static final String TIME_STAMP = "timestamp";
	public static final String MESSAGE_ID = "messageID";
	public static final String CORRELATION_ID = "correlationID";

	// HTTP STATUS
	public static final String OK = "200";
	public static final String NO_CONTENT = "204";
	public static final String BAD_REQUEST = "400";
	public static final String UNAUTHORIZED = "401";
	public static final String METHOD_NOT_ALLOWED = "405";
	public static final String PRECONDITION_FAILED = "412";
	public static final String DUPLICATION_FAILED = "409";
	public static final String UNSUPPORTED_MEDIA_TYPE = "415";
	public static final String INTERNAL_ERROR = "500";

	// PRECONDITION
	public static final String USERNAME = "USERNAME";
	public static final String USERNAME_EXIST = "USERNAME_EXIST";
	public static final String INACTIVE_USER_EXIST = "INACTIVE_USER_EXIST";

	// USER TYPE
	public static final String ADMIN = "ADMIN";
	public static final String SUPER_ADMIN = "SUPER_ADMIN";
	public static final String NORMAL = "NORMAL";

	public static final String NAME = "NAME";
	public static final String TYPE = "TYPE";

	public static final Integer TRUE = 1;
	public static final Integer FALSE = 0;

	public static final String WELCOME_MESSAGE = "Welcome to <span style='color:red; font-size:25px;'>"
			+ " Green Delta Insurance Family. " + "</span> <br/>";

	public static final String[] propsExceptArr = new String[] { "createdBy", "createdAt", "modifiedBy", "modifiedAt" };

}
