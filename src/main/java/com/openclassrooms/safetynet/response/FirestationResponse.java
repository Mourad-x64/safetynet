package com.openclassrooms.safetynet.response;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetynet.domain.Person;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FirestationResponse {
	
	private List<Person> persons = new ArrayList<>();
	private int adultNumber;
	private int childNumber;
	
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	public int getAdultNumber() {
		return adultNumber;
	}
	public void setAdultNumber(int adultNumber) {
		this.adultNumber = adultNumber;
	}
	public int getChildNumber() {
		return childNumber;
	}
	public void setChildNumber(int childNumber) {
		this.childNumber = childNumber;
	}

}
