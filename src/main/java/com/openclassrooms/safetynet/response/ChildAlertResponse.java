package com.openclassrooms.safetynet.response;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.safetynet.domain.Person;

public class ChildAlertResponse {
	
	private List<Person> childrens = new ArrayList<>();
	private List<Person> adults = new ArrayList<>();
	
	public List<Person> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Person> childrens) {
		this.childrens = childrens;
	}
	public List<Person> getAdults() {
		return adults;
	}
	public void setAdults(List<Person> adults) {
		this.adults = adults;
	}
	
	

}
