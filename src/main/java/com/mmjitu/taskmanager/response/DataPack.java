package com.mmjitu.taskmanager.response;

import java.util.List;

public class DataPack<T> {

	private boolean result;
	private List<T> model;
	private List<String> remarks;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public List<T> getModel() {
		return model;
	}

	public void setModel(List<T> model) {
		this.model = model;
	}

	public List<String> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

	public DataPack(boolean result, List<T> model, List<String> remarks) {
		this.result = result;
		this.model = model;
		this.remarks = remarks;
	}
}