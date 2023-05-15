package com.openclassrooms.safetynet.response;

import java.util.HashMap;
import java.util.Map;

import com.openclassrooms.safetynet.domain.Person;

public class FloodStationsResponse {
	
	private Map<String, Map<String, Person>> addresses = new HashMap<>();

	public Map<String, Map<String, Person>> getAddresses() {
		return addresses;
	}

	public void setAddresses(Map<String, Map<String, Person>> addresses) {
		this.addresses = addresses;
	}

	
	

}
