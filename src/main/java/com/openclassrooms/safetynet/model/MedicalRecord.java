package com.openclassrooms.safetynet.model;

import com.amazonaws.util.json.JSONObject;

public class MedicalRecord {
	
	private JSONObject record;

	public JSONObject getRecord() {
		return record;
	}

	public void setRecord(JSONObject jsonObject) {
		this.record = jsonObject;
	}
	
	

}
