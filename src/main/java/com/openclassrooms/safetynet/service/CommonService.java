package com.openclassrooms.safetynet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.DataManager;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.House;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.response.ChildAlertResponse;
import com.openclassrooms.safetynet.response.CommunityEmailResponse;
import com.openclassrooms.safetynet.response.FireResponse;
import com.openclassrooms.safetynet.response.FirestationResponse;
import com.openclassrooms.safetynet.response.FloodStationsResponse;
import com.openclassrooms.safetynet.response.PersonInfoResponse;
import com.openclassrooms.safetynet.response.PhoneAlertResponse;

@RestController
@RequestMapping("/")
public class CommonService {
	
	@Autowired
	private DataManager dataManager; 
    
    
    
    @GetMapping("firestation")  
    
    public FirestationResponse firestation(@RequestParam int station) throws Exception {
    	FireStation fireStation = dataManager.getFireStations().get(station);
    	List<House> houses = fireStation.getHouses();
    	List<Person> persons = new ArrayList<>();
    	
    	for(House house : houses) {
    		persons.addAll(house.getPersons());
    	}
    	
    	int adult = 0;
    	int child = 0;
    	
    	for(Person person : persons) {
    		if(person.getAge() > 18) {
    			adult+=1;
    		}else {
    			child+=1;
    		}
    	}
    	
    	List<Person> personDTO = new ArrayList<>();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	
    	for(Person person : persons) {
    		String personAsString = mapper.writeValueAsString(person);
    		Person clonedPerson = mapper.readValue(personAsString, Person.class);    		
    		clonedPerson.setAllergies(null);
    		clonedPerson.setMedications(null);
    		clonedPerson.setDateOfBirth(null);
    		clonedPerson.seteMail(null);    		
    		personDTO.add(clonedPerson);
    	}
    	
    	
    	
    	FirestationResponse result = new FirestationResponse();
    	result.setPersons(personDTO);
    	result.setAdultNumber(adult);
    	result.setChildNumber(child);
        return result;
    }
    
    @GetMapping("childAlert")  
    
    public ChildAlertResponse firestation(@RequestParam String address) throws Exception {
    	House house = dataManager.getHouses().get(address);    	
    	ChildAlertResponse result = new ChildAlertResponse();    	
    	
    	List<Person> adults = new ArrayList<>();
    	List<Person> childrens = new ArrayList<>();
    	
    	for(Person person : house.getPersons()) {
    		if(person.getAge() > 18) {
    			adults.add(person);
    		}else {
    			childrens.add(person);
    		}
    	}    	
    	
    	result.setAdults(adults);
    	result.setChildrens(childrens);
    	
    	
    	return result;  		
    	
        
    }
    
 @GetMapping("phoneAlert")  
    
    public PhoneAlertResponse phoneAlert(@RequestParam int station) throws Exception {
    	FireStation firestation = dataManager.getFireStations().get(station);    	
    	PhoneAlertResponse result = new PhoneAlertResponse();    	
    	
    	List<String> phones = new ArrayList<>();
    	
    	
    	for(House house : firestation.getHouses()) {
    		for(Person person : house.getPersons()) {
    			result.addPhone(person.getPhoneNumber());    			
    		}
    	}    	
    	
    	return result;    	
        
    }
 
 @GetMapping("fire")  
 
 public FireResponse fire(@RequestParam String address) throws Exception {
 	House house = dataManager.getHouses().get(address);    	
 	FireResponse result = new FireResponse();    	
 	
 	List<Person> persons = new ArrayList<>();
 	ObjectMapper mapper = new ObjectMapper(); 	
 	
 	
 	for(Person person : house.getPersons()) {
 		
 		String personAsString = mapper.writeValueAsString(person);
		Person clonedPerson = mapper.readValue(personAsString, Person.class);				
		clonedPerson.setDateOfBirth(null);
		clonedPerson.seteMail(null);		
		clonedPerson.setFirstName(null);
		
		persons.add(clonedPerson); 		 			
 	}
 	
 	result.setPersons(persons);
 	result.setStationNumber(house.getStationNumber());
 	   	
 	
 	return result;    	
     
 }
 
 
@GetMapping("flood/stations")  
 
 public FloodStationsResponse fire(@RequestParam List<Integer> stations) throws Exception {
	
	Map<String, Map<String, Person>> adresses = new HashMap<>();
	Map<String, Person> personsByName = new HashMap<>();	
	FloodStationsResponse result = new FloodStationsResponse();
	ObjectMapper mapper = new ObjectMapper();
	
	for(int stationNumber:stations) {
		FireStation firestation = dataManager.getFireStations().get(stationNumber);
		for(House house: firestation.getHouses()) {			
			for(Person person: house.getPersons()) {				
				String personAsString = mapper.writeValueAsString(person);
				Person clonedPerson = mapper.readValue(personAsString, Person.class);
				clonedPerson.setDateOfBirth(null);
				clonedPerson.seteMail(null);				
				clonedPerson.setFirstName(null);
				
				personsByName.put(clonedPerson.getLastName(), clonedPerson);				
				
			}
			
			adresses.put(house.getAddress(), personsByName);
		}
	}
	
	
	
 	
 	   	
 	result.setAddresses(adresses);
 	return result;    	
     
 }

 
@GetMapping("personInfo")  

public PersonInfoResponse personInfo(@RequestParam String firstName,@RequestParam String lastName) throws Exception {
	
	PersonInfoResponse result = new PersonInfoResponse();
	Map<String, House> houses = dataManager.getHouses();	
	
	
	for (Map.Entry<String, House> entry : houses.entrySet()) {	    
	    House house = entry.getValue();
	    
	    for(Person person : house.getPersons()) {
	    	if(person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
	    		result.setAddress(entry.getKey());
	    		result.setLastName(person.getLastName());
	    		result.setAge(person.getAge());
	    		result.seteMail(person.geteMail());
	    		result.setMedications(person.getMedications());
	    		result.setAllergies(person.getAllergies());
	    	}
	    }
	    
	}
	
	
	return result;    	
    
}


@GetMapping("communityEmail")  

public CommunityEmailResponse communityEmail(@RequestParam String city) throws Exception {
	
	CommunityEmailResponse result = new CommunityEmailResponse();
	Map<String, House> houses = dataManager.getHouses();	
	
	
	for (Map.Entry<String, House> entry : houses.entrySet()) {	    
	    House house = entry.getValue();
	    
	    for(Person person : house.getPersons()) {
	    	if(person.getCity().equalsIgnoreCase(city)) {
	    		result.getEmails().add(person.geteMail());	    		
	    	}
	    }
	    
	}
	
	
	return result;    	
    
}


}