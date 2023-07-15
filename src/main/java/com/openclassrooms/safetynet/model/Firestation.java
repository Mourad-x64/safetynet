package com.openclassrooms.safetynet.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "firestation")
public class Firestation {	

	    @Id
	    private String id;
	    private String address;
	    private int firestationNumber;
	    
	    
	    
	    
		public Firestation(String address, int firestationNumber) {
			super();
			this.address = address;
			this.firestationNumber = firestationNumber;
			this.id = UUID.randomUUID().toString();
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public int getFirestationNumber() {
			return firestationNumber;
		}
		public void setFirestationNumber(int firestationNumber) {
			this.firestationNumber = firestationNumber;
		}	
	
}
