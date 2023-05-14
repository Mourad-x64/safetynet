package com.openclassrooms.safetynet.response;

import java.util.ArrayList;
import java.util.List;

public class PhoneAlertResponse {
	
	private List<String> phones = new ArrayList<>();

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	public void addPhone(String phone) {
		phones.add(phone);
	}
	
	

}
