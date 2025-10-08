package com.mmjitu.taskmanager.utility;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmjitu.taskmanager.entity.common.ObjectAction;
import com.mmjitu.taskmanager.internalvm.ClaimsModuleUserDto;

@Service
public class ServiceUtil {	
	
	public List<String> processGenericErrorMessageByHttpStatusCode(int httpStatus){
		List<String> msg = new ArrayList<>();
        
        if (httpStatus==400) {
        	msg.add("Bad Request, Please check all mandatory fields. Required field can not be null!");            
            
        }else if(httpStatus==401) {
        	msg.add("Unauthorized, Credential does not matched or user does not has the priviledge to proceed!");            
            
        }else if(httpStatus==412) {
        	msg.add("Precondition faied. Failed to validate data!");             
        }else if(httpStatus==415) {
        	msg.add("Method not allowed.!");            
            
        }else if(httpStatus==500) {
        	msg.add("Internal Server Error. Please be patience!");            
            
        }else if(httpStatus==408) {
        	msg.add("Request Timedout!");            
        }else {
        	msg.add("Unknown!");            
        }
        return msg;
	}
	
	public Map<String, Object> getResponseBody(String response) {
        try {
            if (response != null) {
            	ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> resultMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
                return resultMap;
            }else{
            	Map<String, Object> resultMap = new HashMap<>();
            	resultMap.put("responseCode", "412");
            	resultMap.put("message", Arrays.asList("Some error occured but can not read the message."));

            	return resultMap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	//------------------ Prepare Model Data when exception occurs
	public void addResponseDataForException(Exception e, CommonServiceModel serviceModel) {
		serviceModel.setMessage(e.getLocalizedMessage());
		serviceModel.getData().put(GlobalConstants.DATA, new ArrayList<>());
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.INTERNAL_ERROR);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
	}
	//--------------- Return 500 for any failure with message
	public void addResponseDataForFailure(Object data, String msg, CommonServiceModel serviceModel) {
		addMessage(msg, serviceModel);
		serviceModel.getData().put(GlobalConstants.DATA, data);
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.INTERNAL_ERROR);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
	}
	
	// UNAUTHORIZED: CommonServiceModel , return Map
	public Map<String, Object> getServiceReturnForUnauthorized(String msg, CommonServiceModel serviceModel) {
		addMessage(msg, serviceModel);
		
		serviceModel.getData().put(GlobalConstants.DATA, new ArrayList<>());
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.UNAUTHORIZED);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
		
		return getServiceReturn(serviceModel);
	}
	//PRECONDITION FAILED [1]: CommonServiceModel
	public Map<String, Object> getServiceReturnForPreconditionFailed(CommonServiceModel serviceModel) {
		serviceModel.getData().put(GlobalConstants.DATA, new ArrayList<>());
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.PRECONDITION_FAILED);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
		
		return getServiceReturn(serviceModel);
	}

	//PRECONDITION FAILED [3]: String, CommonServiceModel return map
	public Map<String, Object> getServiceReturnForPreconditionFailed(String msg, CommonServiceModel serviceModel) {
		addMessage(msg, serviceModel);
		serviceModel.getData().put(GlobalConstants.DATA, new ArrayList<>());
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.PRECONDITION_FAILED);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
		
		return getServiceReturn(serviceModel);
	}
	
	//PRECONDITION FAILED [3]: String, CommonServiceModel return map
	public Map<String, Object> getServiceReturnForDuplicationFailed(String msg, CommonServiceModel serviceModel) {
			addMessage(msg, serviceModel);
			serviceModel.getData().put(GlobalConstants.DATA, new ArrayList<>());
			serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
			serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.DUPLICATION_FAILED);
			
			serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
			serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
			serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
			
			return getServiceReturn(serviceModel);
	}
	
	public Map<String, Object> getServiceReturnForPreconditionFailed(List<String> msg, CommonServiceModel serviceModel) {
		addMessages(msg, serviceModel);
		serviceModel.getData().put(GlobalConstants.DATA, new ArrayList<>());
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.PRECONDITION_FAILED);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
		
		return getServiceReturn(serviceModel);
	}
	
	// SUCCESS : Object, String, CommonServiceModel
	public void addResponseDataForSuccess(Object data, String message, CommonServiceModel serviceModel) {
		addMessage(message, serviceModel);
		serviceModel.getData().put(GlobalConstants.DATA, data);
		serviceModel.getData().put(GlobalConstants.MESSAGE, serviceModel.getMessage());
		serviceModel.getData().put(GlobalConstants.RESPONSE_CODE, GlobalConstants.OK);
		
		serviceModel.getData().put(GlobalConstants.TIME_STAMP, new Timestamp(System.currentTimeMillis()).toString());
		serviceModel.getData().put(GlobalConstants.MESSAGE_ID, "");
		serviceModel.getData().put(GlobalConstants.CORRELATION_ID, "");
	}
	
	public Map<String, Object> getServiceReturn(CommonServiceModel serviceModel) {
		return serviceModel.getData();
	}
	
	public void addMessage(String msg, CommonServiceModel serviceModel) {
		serviceModel.getMessage().add(msg);
	}
	
	public void addMessages(List<String> msgs, CommonServiceModel serviceModel) {
		serviceModel.setMessage(msgs);
	}

	// SET OBJECT ACTION
	public void setCreateObjectAction(ObjectAction actions, Long userId) {
		//actions.setCreatedBy(userId);
		actions.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	}

	public void setUpdateObjectAction(ObjectAction actions, Long userId) {
		//actions.setModifiedBy(userId);
		actions.setModifiedAt(new Timestamp(System.currentTimeMillis()));
	}
	
	public void setCreateObjectAction(ObjectAction actions, ClaimsModuleUserDto user) {
		//actions.setCreatedBy(user.getId());
		actions.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	}

	public void setUpdateObjectAction(ObjectAction actions, ClaimsModuleUserDto user) {
		//actions.setModifiedBy(user.getId());
		actions.setModifiedAt(new Timestamp(System.currentTimeMillis()));
	}
	
}