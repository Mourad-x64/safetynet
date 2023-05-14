package com.openclassrooms.safetynet.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.amazonaws.util.json.JSONObject;

public class FireStation {
	
	private List<House> houses = new ArrayList<>();
	

	public List<House> getHouses() {
		return houses;
	}	
	
	public void addHouse(House house) {
		houses.add(house);
	}

	@Override
	public String toString() {
		
		return houses.toString();
	}
	
	
	

}
