package com.openclassrooms.safetynet.model;

import org.codehaus.plexus.component.annotations.Component;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection= "person")
public class Person {	

	    @Id
	    private String id;
	    private String firstName;
	    private String lastName;
	    private String eMail;
	    private String age;
	    private String dateOfBirth;
	    private String phoneNumber;
	    private String address;
	    private String city;
	    private String zip; 
	    
	    		
		
		
		public Person(String firstName, String lastName, String eMail, String age, String dateOfBirth,
				String phoneNumber, String address, String city, String zip) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.eMail = eMail;
			this.age = age;
			this.dateOfBirth = dateOfBirth;
			this.phoneNumber = phoneNumber;
			this.address = address;
			this.city = city;
			this.zip = zip;
		}
		
		
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
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
		public String geteMail() {
			return eMail;
		}
		public void seteMail(String eMail) {
			this.eMail = eMail;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
	    
	    
	
	
}
