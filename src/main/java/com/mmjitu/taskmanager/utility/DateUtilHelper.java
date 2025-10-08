package com.mmjitu.taskmanager.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class DateUtilHelper {

	    static Random r = new Random();

	    public synchronized static String getDate() {
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date date = new java.util.Date();
	        return dateFormat.format(date);
	    }

	    public static synchronized String getTimeStamp() {
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
	        java.util.Date date = new java.util.Date();
	        return dateFormat.format(date);
	    }
	    
	    public static synchronized String getSqlTimeStamp() {
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
	        java.util.Date date = new java.util.Date();
	        return dateFormat.format(date);
	    }
	    
	    public static synchronized String getCurrentDay() {
	        DateFormat dateFormat = new SimpleDateFormat("MMdd");
	        java.util.Date date = new java.util.Date();
	        return dateFormat.format(date);
	    }

	    public static synchronized String generateId() {
	        String format = "yyyyMMddHHmmssS";
	        DateFormat dateFormat = new SimpleDateFormat(format);
	        java.util.Date date = new java.util.Date();
	        return dateFormat.format(date);
	    }

	    public static synchronized String getLogTimeStamp() {
			String format = "dd MMM yy";
			DateFormat dateFormat = new SimpleDateFormat(format);
			java.util.Date date = new java.util.Date();
			return dateFormat.format(date);
		}
	    
}
