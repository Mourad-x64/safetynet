package com.openclassrooms.safetynet.response;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetynet.model.Person;

public class FireResponse {
	
	private List<Person> persons = new ArrayList<>();
	private int stationNumber = 0;
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	public int getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	
	public void addPerson(Person person) {
		persons.add(person);
	}

}
