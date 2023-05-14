package com.openclassrooms.safetynet.model;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.amazonaws.util.IOUtils;

import jakarta.annotation.PostConstruct;

@Component
public class DataManager {

	Map<Integer, FireStation> fireStations = new HashMap<>();
	Map<String, House> houses = new HashMap<>();
	
	private JSONObject jsonData;

	public DataManager() {
		

	}

	@PostConstruct
	public void init() {
		initjsonData();
		try {
			buildObjects();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<Integer, FireStation> getFireStations() {
		return fireStations;
	}
	
	public Map<String, House> getHouses() {
		return houses;
	}

	public void initjsonData() {
		String uri = "https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json";
		String json = null;
		try {
			json = IOUtils.toString(new URL(uri).openStream());
			jsonData = new JSONObject(json);
								
			
		} catch (Exception e) {
			// nothing to do
		}	
		
	}
	
		
	public void buildObjects() throws JSONException {
		JSONArray jsonArray = jsonData.getJSONArray("firestations");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			
			int stationNumber = jsonArray.getJSONObject(i).getInt("station");
			String address = jsonArray.getJSONObject(i).getString("address");
			
			FireStation fireStation = fireStations.get(stationNumber);
			
			if(fireStation == null) {
				fireStation = new FireStation();
				fireStations.put(stationNumber, fireStation);
			}
			
			House house = new House();
			house.setAddress(address);
			house.setStationNumber(stationNumber);
			
			house.setPersons(getPersonsByAddress(address, jsonData));
			
			fireStation.addHouse(house);
			houses.put(address, house);			
			
		}		
	}	
				
				

	private List<Person> getPersonsByAddress(String address, JSONObject jsonData) throws JSONException {
		List<Person> result = new ArrayList<>();
		JSONArray jsonArray = jsonData.getJSONArray("persons");
		
		for (int i = 0; i < jsonArray.length(); i++) {
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			if(jsonObject.getString("address").equalsIgnoreCase(address)) {
				Person person = new Person();
				person.setFirstName(jsonObject.getString("firstName"));
				person.setLastName(jsonObject.getString("lastName"));
				person.setPhoneNumber(jsonObject.getString("phone"));
				person.seteMail(jsonObject.getString("email"));				
				person.setCity(jsonObject.getString("city"));
				
				String dateOfBirth = getDateOfBirth(person.getFirstName(), person.getLastName(), jsonData);				
				person.setDateOfBirth(dateOfBirth);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
				LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
				LocalDate curDate = LocalDate.now();
				int age = Period.between(dob, curDate).getYears();
				person.setAge(age);
				
				JSONArray medications = getMedications(person.getFirstName(), person.getLastName(), jsonData);
				List<String> medicationList = new ArrayList<>();
				for (int j = 0; j < medications.length(); j++) {
					medicationList.add(medications.get(j).toString());
				}
				person.setMedications(medicationList);
				
				JSONArray allergies = getAllergies(person.getFirstName(), person.getLastName(), jsonData);
				List<String> allergyList = new ArrayList<>();
				for (int j = 0; j < allergies.length(); j++) {
					allergyList.add(allergies.get(j).toString());
				}
				person.setAllergies(allergyList);
				
				result.add(person);
				
			}			
		}
		
		return result;
	}

	private String getDateOfBirth(String firstName, String lastName, JSONObject jsonData) throws JSONException {
		
		
		JSONArray jsonArray = jsonData.getJSONArray("medicalrecords");
		
		for (int i = 0; i < jsonArray.length(); i++) {			
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			if(jsonObject.getString("firstName").equalsIgnoreCase(firstName) && jsonObject.getString("lastName").equalsIgnoreCase(lastName)) {
				return jsonObject.getString("birthdate");
				
			}			
		}
		
		return null;
	}
	
	private JSONArray getMedications(String firstName, String lastName, JSONObject jsonData) throws JSONException {
		JSONArray jsonArray = jsonData.getJSONArray("medicalrecords");
		
		for (int i = 0; i < jsonArray.length(); i++) {			
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			if(jsonObject.getString("firstName").equalsIgnoreCase(firstName) && jsonObject.getString("lastName").equalsIgnoreCase(lastName)) {
				return jsonObject.getJSONArray("medications");
				
			}			
		}
		
		return null;
	}
	
	private JSONArray getAllergies(String firstName, String lastName, JSONObject jsonData) throws JSONException {
		JSONArray jsonArray = jsonData.getJSONArray("medicalrecords");
		
		for (int i = 0; i < jsonArray.length(); i++) {			
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			if(jsonObject.getString("firstName").equalsIgnoreCase(firstName) && jsonObject.getString("lastName").equalsIgnoreCase(lastName)) {
				return jsonObject.getJSONArray("allergies");
				
			}			
		}
		
		return null;
	}
	

	

}
