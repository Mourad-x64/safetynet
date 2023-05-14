package com.openclassrooms.safetynet.model;

import java.util.ArrayList;
import java.util.List;

public class House {
	
	private String address;
	private List<Person> persons = new ArrayList<>();
	private int stationNumber = 0;
	
	

	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return address+": "+persons.toString();
	}
	
}
