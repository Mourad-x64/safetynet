package com.openclassrooms.safetynet.response;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetynet.domain.Person;

public class PersonInfoResponse {
	
	private String address = "";
	private String lastName = "";
	private int age = 0;
	private String eMail = "";
	private List <String> medications;
	private List <String> allergies;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	
	

	
}
