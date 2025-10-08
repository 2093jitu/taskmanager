package com.mmjitu.taskmanager.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mmjitu.taskmanager.internalvm.ClaimsModuleUserDto;

public class CommonServiceModel {

	private Map<String, Object> data = new HashMap<String, Object>();
	private List<String> message = new ArrayList<>();
	private List<ClaimsModuleUserDto> users;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

	public void setMessage(String message) {
		this.message.add(message);
	}

	public List<ClaimsModuleUserDto> getUsers() {
		return users;
	}

	public void setUsers(List<ClaimsModuleUserDto> users) {
		this.users = users;
	}

}
