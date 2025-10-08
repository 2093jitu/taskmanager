package com.mmjitu.taskmanager.utility;



import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class Utility {
	
	private static final Logger log = LoggerFactory.getLogger(Utility.class);		

	public static List<Long> objectArrayToListInt(Object[] objArr){
		List<Long> listInt = new ArrayList<Long>();
		for (Object moduleId : objArr) {
			listInt.add(Long.valueOf(moduleId.toString()));
		}
		return listInt;
	}
	
	public static List<Integer> intArrToListInt(int[] intArr){
		List<Integer> intList = new ArrayList<Integer>(intArr.length);
		for (int i : intArr)
		{
		    intList.add(i);
		}
		return intList;
	}
	
	public static String removeSpaceByUScoreAndMakeUpperCaseAndTrim(String givenText) {
		if (givenText != null)
			return givenText.replaceAll("\\s", "_").toUpperCase().trim();
		else
			return givenText;
	}
	
	public static String subStringAndRemoveSpaceToAddUScoreTrim(String givenText, int subStrLnth) {
		String str=null;
		if (givenText != null && givenText.length()>0) {
			if (givenText.length()>5) {
				str = givenText.substring(0,5);
			}else {
				str = givenText.substring(0, givenText.length());
			}
		}
		return str.replaceAll("\\s", "_").toUpperCase().trim();
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
		
	public static String formatNumber(String phoneNo){
		try{
			if(phoneNo.startsWith("+")) phoneNo = phoneNo.substring(1);
			if(phoneNo.startsWith("880")) return phoneNo.substring(3);
			if(phoneNo.startsWith("+880")) return phoneNo.substring(4);
            if(phoneNo.startsWith("00880")) return phoneNo.substring(5);
            if(phoneNo.startsWith("0880")) return phoneNo.substring(4); // changed due to user's number style
			if(phoneNo.startsWith("0")) return phoneNo.substring(1);
			return phoneNo.trim();
		} catch(Exception e){
			System.out.println("Invalid number to format: " + phoneNo);
		}
		return "-1";
	}
	
	public static Date parseDateOnly(String dateStr) {
		
        Date date = null;
        
		try {
			if(!dateStr.isEmpty()) {
				date=new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        //System.out.println("Successfully Parsed Date " + date);
        return date;
    }
	
	public static String formatDateString(String dateStr) {
		
        String date = "";
        
		try {
			if(!dateStr.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				
				Date parsedDate = sdf.parse(dateStr);
				
				SimpleDateFormat format = new SimpleDateFormat("MMMM d, y");
				System.out.println(format.format(parsedDate));
				
				date = format.format(parsedDate);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return date;
    }
		
	public static String removeStartingAndEndingCommaFromString(String givenStr) {
		
		if(givenStr==null || givenStr.equals("")) {
			return "";
		}
		
		String input = givenStr;
		String[] elements = input.split(",");
		List<String> trimmedElements = new ArrayList<>();

		for (String element : elements) {
		    String trimmedElement = element.trim();
		    if (!trimmedElement.isEmpty()) {
		        trimmedElements.add(trimmedElement);
		    }
		}

		String output = String.join(", ", trimmedElements);

		System.out.println(output); // Output: "abc, dtd"

        return output;
    }
		
	public static long daysCount(String dateBeforeString, String dateAfterString) {
	
		//String dateBeforeString = "2020-05-15";
		//String dateAfterString = "2020-05-31";
			
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString);
		LocalDate dateAfter = LocalDate.parse(dateAfterString);
			
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		noOfDaysBetween += 1; // adding 1 for completing year

		return noOfDaysBetween;
	}
	
    public static int getMonthDifference(java.sql.Date startDate, java.sql.Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        int startYear = startCal.get(Calendar.YEAR);
        int startMonth = startCal.get(Calendar.MONTH);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        int endYear = endCal.get(Calendar.YEAR);
        int endMonth = endCal.get(Calendar.MONTH);

        int yearDifference = endYear - startYear;
        int monthDifference = endMonth - startMonth;

        return yearDifference * 12 + monthDifference;
    }
	
	public static int daysCount(java.sql.Date dateBeforeString, java.sql.Date dateAfterString) throws ParseException {
		
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString.toString());
		LocalDate dateAfter = LocalDate.parse(dateAfterString.toString());
			
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		//noOfDaysBetween += 1; // adding 1 for completing year

		return (int) noOfDaysBetween;
	}
	
	public static long daysCount2(java.sql.Date dateBeforeString, java.sql.Date dateAfterString) throws ParseException {
		
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString.toString());
		LocalDate dateAfter = LocalDate.parse(dateAfterString.toString());
			
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		//noOfDaysBetween += 1; // adding 1 for completing year

        LocalDate localDate = dateAfterString.toLocalDate();
        int year = localDate.getYear();
        if (isLeapYear(year)) {
        	if (isAfterFebruary28(java.sql.Date.valueOf(dateAfter))) {
            	//noOfDaysBetween = noOfDaysBetween+1;
			}
		}
		System.out.println("------------- Days Count -------------------> "+ noOfDaysBetween);
		return noOfDaysBetween;
	}
	
    public static boolean isAfterFebruary28(Date date) {
        Date feb28 = createFebruary28CurrentYear();
        // Check if the given date is after February 28th
        return date.after(feb28);
    }
    
    private static Date createFebruary28CurrentYear() {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        // Creating a java.sql.Date object for February 28th of the current year
        LocalDate feb28 = LocalDate.of(currentYear, 2, 28);

        // Converting LocalDate to java.sql.Date
        Date sqlDate = java.sql.Date.valueOf(feb28);
        return sqlDate;
    }
    
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
	public static int ageCount(java.sql.Date dob) {
		
		try {
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		       Date startDate = sdf.parse(dob.toString());
		       Date endDate = sdf.parse(new java.sql.Date(System.currentTimeMillis()).toString());

		       OffsetDateTime startOdt = startDate.toInstant().atOffset(ZoneOffset.UTC);
		       OffsetDateTime endOdt = endDate.toInstant().atOffset(ZoneOffset.UTC);

		       int years = Period.between(startOdt.toLocalDate(), endOdt.toLocalDate()).getYears();
		       return years;
		} catch (ParseException e) {
			return 0;
		}
	}
		
	public static int ageCountTillDocumentFromDate(java.sql.Date dob , String documentFromDateStr) {
		
		try {
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");			   
		       Date startDate = sdf.parse(dob.toString());
		       Date documentFromDate = sdf.parse(documentFromDateStr.toString());
		       Date endDate = sdf.parse(new java.sql.Date(documentFromDate.getTime()).toString());

		       OffsetDateTime startOdt = startDate.toInstant().atOffset(ZoneOffset.UTC);
		       OffsetDateTime endOdt = endDate.toInstant().atOffset(ZoneOffset.UTC);

		       int years = Period.between(startOdt.toLocalDate(), endOdt.toLocalDate()).getYears();
		       int months = Period.between(startOdt.toLocalDate(), endOdt.toLocalDate()).getMonths();
		       
		       if(months >= 6) {
		    	   years = years+1;   
		       }
		       
		       return years;
		} catch (ParseException e) {
			return 0;
		}
	}
		
	public static long daysCountPlusMonth(String dateBeforeString, Integer addedMonth) {	
		
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString);
		LocalDate dateAfter = LocalDate.parse(dateBeforeString).plusMonths(addedMonth);		
			
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
		noOfDaysBetween += 1; // adding 1 for completing year

		return noOfDaysBetween;
		
	}
	
	public static java.sql.Date daysPlusDays(String dateBeforeString, Integer addedDays) {	
		//Parsing the date
		LocalDate dateAfter = LocalDate.parse(dateBeforeString).plusDays(addedDays);	
		return java.sql.Date.valueOf( dateAfter );
	}
	
	public static String getDateString() {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTime = dtf.format(date);
		return dateTime;

	}
	
	public static String getDateStr(Date date) {
		if (date!=null) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		    return dateFormat.format(date);
		}else {
		    return "";
		}
	}
	
	public static String getDateStr(java.sql.Date date) {
		if (date!=null) {
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		    return dateFormat.format(date);
		}else {
		    return "";
		}   
	}
	
	public static String getCommaSeparatedDoubleValueStrFromDouble3Digit(Double value) {
		  DecimalFormat decimalFormat = new DecimalFormat("#.###");
		  decimalFormat.setGroupingUsed(true);
		  decimalFormat.setGroupingSize(3);
		  decimalFormat.setMinimumFractionDigits(2);
		  
		  return decimalFormat.format(value);
	}
	
	public static String getCommaSeparatedDoubleValueStrFromDouble(Double value) {
		  DecimalFormat decimalFormat = new DecimalFormat("#.##");
		  decimalFormat.setGroupingUsed(true);
		  decimalFormat.setGroupingSize(3);
		  decimalFormat.setMinimumFractionDigits(2);
		  
		  return decimalFormat.format(value);
	}
	
	public static String getCommaSeparatedDoubleValueStrFromDouble(Double value, int requiredDecimalPoint) {
		  DecimalFormat decimalFormat = new DecimalFormat("#.####");
		  decimalFormat.setGroupingUsed(true);
		  decimalFormat.setGroupingSize(3);
		  decimalFormat.setMinimumFractionDigits(requiredDecimalPoint);
		  
		  return decimalFormat.format(value);
	}
	
	public static Double fromStrintNumberToDouble(String numStr) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number;
		Double retVal = 0.0;
		try {
			number = format.parse(numStr);
			retVal = number.doubleValue();
	        
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public static Double roundDoubleValueUpToGivenDecimalDigitFormat(Double num, String decimalUpto) {
		Double roundedVal = 0.0;
		try {
			if (num != 0.0) {
				roundedVal = Double.valueOf(String.format("%."+decimalUpto+"f", num));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundedVal;
	}
    
	public static Double roundDoubleValueUpTo2DigitFormat(Double num) {
		Double roundedVal = 0.0;
		try {
			if (num != 0.0) {
				roundedVal = Double.valueOf(String.format("%.2f", num));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundedVal;
	}

	public static Double roundDoubleValue(Double num) {
		Double roundedVal = 0.0;
		try {
			if (num != 0.0) {
				roundedVal = (double) Math.round(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundedVal;
	}
	
	public static Double roundDoubleValueUpToGivenDecimal(Double num, int precisionVal) {
		Double roundedVal = 0.0;
		try {
			double precision = Math.pow(10.0, precisionVal);
			if (num != 0.0) {
				roundedVal = (double) Math.round(num*precision)/precisionVal;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roundedVal;
	}
	
	public static boolean isRoundValueGiven(Double val) {
		boolean isRound = val % 1==0?true:false;
		return isRound;
	}
	
	public static String getCurrencyTypeShortCode(String currencyType) {
		String shortCode = "";
		try {
			if (currencyType != null) {
				Map<String, String> map =  new HashMap<>();
				
				map.put("BDT", "TK");
				map.put("USD", "US");
				map.put("EURO", "EURO");
				map.put("INR", "IN");
				
				Optional<String> optionalIsbn = map.entrySet().stream()
						  .filter(e -> e.getKey().equals(currencyType))
						  .map(Map.Entry::getValue).findFirst();
				
				if (optionalIsbn.isPresent()) {
					shortCode = optionalIsbn.get();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return shortCode;
	}
	
	public static String getCurrencySymbol(String currencyType) {
		String symbol = "";
		try {
			if (currencyType != null) {
				Map<String, String> map =  new HashMap<>();
				
				map.put("BDT", "৳");
				map.put("USD", "$");
				map.put("EURO", "€");
				map.put("INR", "₹");
				
				Optional<String> optionalIsbn = map.entrySet().stream()
						  .filter(e -> e.getKey().equals(currencyType))
						  .map(Map.Entry::getValue).findFirst();
				
				if (optionalIsbn.isPresent()) {
					symbol = optionalIsbn.get();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symbol;
	}
	
	public static String getTitleCase(String givenText) {
		String titleCaseText = "";
			if (givenText != null) {
				char first = Character.toUpperCase(givenText.charAt(0));
				titleCaseText = first + givenText.substring(1);
			}
		return titleCaseText;
	}
	
	public static long dayDifference(String dateBeforeString, String dateAfterString) {
		
		//Parsing the date
		LocalDate dateBefore = LocalDate.parse(dateBeforeString);
		LocalDate dateAfter = LocalDate.parse(dateAfterString);
			
		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);	
		return noOfDaysBetween;
		
	}
	
	public static Double stampAmountCalculation(Double netPremium) {
		
		Double stamp = 0.0;
		
		if(netPremium == null || netPremium==0.0) {
			return stamp;
		}
		
		if(netPremium <= 25000.0) {			
			stamp=20.0;
			return stamp;
		}
		
		if(netPremium > 25000.0) {			
			stamp =20.0;			
			Double newNetPremium = netPremium-25000;
			stamp = stamp+(Math.ceil((newNetPremium/5000))*20);	
			return stamp;
		}	
		
		return stamp;		
	}
	
	public static boolean isExpire(String date){
	    if(date.isEmpty() || date.trim().equals("")){
	        return false;
	    }else{
	            SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd"); // Jan-20-2015 1:30:55 PM
	               Date d=null;
	               Date d1=null;
	            String today=   getToday("yyyy-MM-dd");
	            try {
	                System.out.println("expdate>> "+date);
	                System.out.println("today>> "+today+"\n\n");
	                d = sdf.parse(date);
	                d1 = sdf.parse(today);
	                if(d1.compareTo(d) <0){// not expired
	                    return false;
	                }else if(d.compareTo(d1)==0){// both date are same
	                            if(d.getTime() < d1.getTime()){// not expired
	                                return false;
	                            }else if(d.getTime() == d1.getTime()){//expired	                                
	                                return true;
	                            }else{//expired
	                                return true;	                                
	                            }
	                }else{//expired
	                    return true;
	                }
	            } catch (ParseException e) {
	                e.printStackTrace();                    
	                return false;
	            }
	    }
	}
		
	public static String getToday(String format){
	     Date date = new Date();
	     return new SimpleDateFormat(format).format(date);
	 }
	 
	public static Connection getJDBCConnection() {

			String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://36.255.69.198:1433";
			String username = "sa";
			String password = "Admin1234";

			try {
				Class.forName(driverClassName);

			} catch (ClassNotFoundException e) {
				System.out.println("Where is your MSSQL JDBC Driver?");
			}

			Connection connection = null;

			try {
				connection = DriverManager.getConnection(url,username, password);

			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
			}
			if (connection != null) {
				return connection;
			} else {
				System.out.println("Failed to make connection!");
			}
			return null;
	}
	 
	public static Map<String, String> getDepartmentClassMap(){
		 Map<String, String> map = new HashMap<>();
		 
		 map.put("AVN" , "Aviation" );
		 map.put("ENG" , "Engineering" );
		 map.put("FIR" , "Fire" );
		 map.put("MAR" , "Marine" );
		 map.put("MISC" ,"Miscellaneous" );
		 map.put("MTR" , "Motor" );		 
		 
		 return map;
	 }
	 
	public static String getDepartmentNameByClassName(String classCode){
		 Map<String, String> map = new HashMap<>();
		 
		 map.put("AVN" , "Aviation" );
		 map.put("ENG" , "Engineering" );
		 map.put("FIR" , "Fire" );
		 map.put("MAR" , "Marine" );
		 map.put("MISC" ,"Miscellaneous" );
		 map.put("MTR" , "Motor" );		 
		 
	     String value = map.get(classCode);

		 return value;
	 }
		 
	public static boolean isNotExpired(java.sql.Date expireDate) {
		   
		   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		   Date today = new Date();
		   boolean isNotExpired = false;
		   Date todayWithZeroTime;
			try {
			   todayWithZeroTime = df.parse(df.format(today));
				
			   System.out.println("Current Date: "+ todayWithZeroTime +", Expire Date : "+ expireDate + ", Result Value : "+ todayWithZeroTime.compareTo(expireDate));
			   
			   //value 0 if the argument Date is equal to this Date;
			   //value less than 0 if this Date is before the Date argument;
			   //value greater than 0 if this Date is after the Date argument.
			   isNotExpired = todayWithZeroTime.compareTo(expireDate)>0?false:true;
			   System.out.println("result: "+ isNotExpired);
	
			} catch (ParseException e) {
				e.printStackTrace();
			}

		return isNotExpired;
	 }
		   
	public static java.sql.Date parseDateTime(Date date) {
		   
	        java.sql.Date sqlDate = null;
	        
			try {
				
				sqlDate = new java.sql.Date(date.getTime());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	        return sqlDate;
	}
		 
	public static boolean hasDecimal(double value) {
	        return value % 1 != 0;
	}
	 
    public static String getCurrentDateTimeInSimpleFormat() {
        // Assuming you have a Timestamp instance, you can get it from a database result, for example
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Create a SimpleDateFormat with the desired pattern
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");

        // Format the Timestamp using the SimpleDateFormat
        String formattedTimestamp = dateFormat.format(new Date(timestamp.getTime()));

        // Print the formatted timestamp
        System.out.println("Formatted Timestamp: " + formattedTimestamp);
        return formattedTimestamp;
    }
       
	public static boolean isMobileNumber(String text) {
	        // Define the mobile number regex pattern
	        String mobileNumberPattern = "(\\+8801|8801|01)\\d{9}";

	        // Compile the pattern
	        Pattern pattern = Pattern.compile(mobileNumberPattern);

	        // Match the input text against the pattern
	        return pattern.matcher(text).matches();
	 }
	 
    public static String base64Encode(String input) {
        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }

    public static String base64Decode(String encodedInput) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedInput.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
    
    public static Date getOneYearAgoDate(Date givenDate) {
    	
    	if(givenDate == null) {
    	  return givenDate;	
    	}
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(givenDate);
    	calendar.add(Calendar.YEAR, -1);
    	Date oneYearAgo = calendar.getTime();
    	
    	return oneYearAgo;    	
    } 
    
    public static boolean isThresholdValueExceed(Double valueToCompare, Double valueCompareWith) {
        Double threshold  = 0.09;
        Double  diff = Math.abs(Utility.roundDoubleValueUpToGivenDecimalDigitFormat(valueToCompare - valueCompareWith,"2"));
        if (diff > threshold) {
            return true;
        }
        return false;
    }
        
    public static Double removeCommaAndConvertStringToDouble(String stringNumber) {
    	if (stringNumber!=null && stringNumber.length()>0) {
        	String grossStr = stringNumber.replace(",", "");
        	Double number = Double.parseDouble(grossStr);
        	return number;
		}else {
			return 0.0;
		}

    }
    
    public static Date getStartOfDay(Date givenDate) {
    
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(givenDate);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();    	
    } 
    
    public static Date getEndOfDay(Date givenDate) {
    	
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTime(givenDate);
    	 calendar.set(Calendar.HOUR_OF_DAY, 23);
    	 calendar.set(Calendar.MINUTE, 59);
    	 calendar.set(Calendar.SECOND, 59);
    	 calendar.set(Calendar.MILLISECOND, 999);     	 
    	 return calendar.getTime();    	
    } 
    
	public static Double convertCurrency(String currencyType, Double amount, Double rate) {
		Double value = 0.0;
		if (currencyType!=null && currencyType.equalsIgnoreCase("BDT")) {
			value = amount * rate;
		}else {
			value = amount / rate;
		}
		return value;
	}
	
	public static List<Long> convertMrIdsStringToListLong(String genText) {
		
        List<Long> idList = new ArrayList<>();

        if (genText!=null) {
        	if (genText!=null && !genText.equals("")) {
                String[] separatedList = genText.split("-");
                
                if (separatedList.length>0) {
                	for (String string : separatedList) {
                		idList.add(Long.valueOf(string));
                    }
    			}
            }
		}
        
        return idList;
    }
	
	public static String concatMrIds(List<Long> mrIds) {

		String genText = "";
		if (mrIds!=null && mrIds.size()>0) {
			genText = mrIds.stream().map(examinerMark -> String.valueOf(examinerMark)).collect(Collectors.joining("-"));
		}
		return genText;
	}
	    
    private static final ObjectMapper objectMapper = new ObjectMapper();
   
    public static <T> T convertJsonToObject(String requestId, String json, Class<T> clazz) {
    	
        try { 
        	objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(json, clazz);            
        } catch (Exception e) {        	
            log.error("Request ID [{}]: Exception occurred while converting JSON to object of type {}. Error: {}", requestId, clazz.getSimpleName(), e.getMessage(), e);
            return null;
        }
    }
}


