package com.openclassrooms.safetynet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "medicalRecord")
public class MedicalRecord {	

	    @Id
	    private String id;
	    private String firstName;
	    private String lastName;
	    private String birthDate;
	    private List<String> medications = new ArrayList<>();
	    private List<String> allergies = new ArrayList<>();
	    
	    
		public MedicalRecord(String firstName, String lastName, String birthDate, List<String> medications,
				List<String> allergies) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthDate = birthDate;
			this.medications = medications;
			this.allergies = allergies;
			this.id = UUID.randomUUID().toString();
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(String birthDate) {
			this.birthDate = birthDate;
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
