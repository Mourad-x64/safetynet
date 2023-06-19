package com.openclassrooms.safetynet;



import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.openclassrooms.safetynet.dao.FirestationRepository;
import com.openclassrooms.safetynet.dao.MedicalRecordRepository;
import com.openclassrooms.safetynet.dao.PersonRepository;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.FirestationService;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;

@DataMongoTest
@Import(TestEntityManagerAutoConfiguration.class)
class SafetynetApplicationTests {
	
	@Autowired	
	PersonRepository personRepo;
	@Autowired
	PersonService personService;
	
	@Autowired	
	FirestationRepository firestationRepo;
	@Autowired
	FirestationService firestationService;
	
	@Autowired	
	MedicalRecordRepository medicalRecordRepo;
	@Autowired
	MedicalRecordService medicalRecordService;
	
	@BeforeEach
	public void dataBaseReset() {
		personService.deleteAllPersons();
	}
	
	public void init() throws Exception {		
		
		personService.createPerson(getDefaultPerson());
	
	}
	
	public Person getDefaultPerson() {
		return  new Person("John", "Doe", "johny@doe.fr", "25", "15/11/1998", "0657863214", "4 rue de la rue", "le mans", "72000");
	}
	
	public Firestation getDefaultFirestation() {
		return  new Firestation("10 rue de la rue", 3);
	}
	
	public MedicalRecord getDefaultMedicalRecord() {
		return  new MedicalRecord("John", "Doe", "15/11/1998",new ArrayList(),new ArrayList());
	}

	@Test
    public void testCreatePerson() {
		
        
		try {
			Person existingPerson = personService.findByLastNameAndFirstName(getDefaultPerson().getFirstName(), getDefaultPerson().getLastName());
			
			Assertions.assertNull(existingPerson);
			
			Person personCreated = personService.createPerson(getDefaultPerson());
			
			Assertions.assertNotNull(personCreated, "The person must have been created");		
			Assertions.assertEquals(getDefaultPerson().getFirstName(), personCreated.getFirstName());
		    Assertions.assertEquals(getDefaultPerson().getLastName(), personCreated.getLastName());
				
			
		} catch (Exception e) {
			Assertions.fail("An error has occured !");
			e.printStackTrace();
		}        
        
        
        
    }
	
	@Test
    public void testCreateFirestation() {
		
        
		try {
			Firestation existingFirestation = firestationService.findFirestationMappingByAddress(getDefaultFirestation().getAddress());
			
			Assertions.assertNull(existingFirestation);
			
			Firestation firestationCreated = firestationService.createFirestationMapping(getDefaultFirestation());
			
			Assertions.assertNotNull(firestationCreated, "The firestation must have been created");		
			Assertions.assertEquals(getDefaultFirestation().getAddress(), firestationCreated.getAddress());
		    Assertions.assertEquals(getDefaultFirestation().getFirestationNumber(), firestationCreated.getFirestationNumber());
				
			
		} catch (Exception e) {
			Assertions.fail("An error has occured !");
			e.printStackTrace();
		}        
        
        
        
    }
	
	@Test
    public void testCreateMedicalRecord() {
		
        
		try {
			MedicalRecord existingMedicalRecord = medicalRecordService.findMedicalRecordByFirstNameAndLastName(getDefaultMedicalRecord().getFirstName(), getDefaultMedicalRecord().getLastName());
			
			Assertions.assertNull(existingMedicalRecord);
			
			MedicalRecord medicalRecordCreated = medicalRecordService.createMedicalRecord(getDefaultMedicalRecord());
			
			Assertions.assertNotNull(medicalRecordCreated, "The medical record must have been created");		
			Assertions.assertEquals(getDefaultMedicalRecord().getFirstName(), medicalRecordCreated.getFirstName());
		    Assertions.assertEquals(getDefaultMedicalRecord().getLastName(), medicalRecordCreated.getLastName());
				
			
		} catch (Exception e) {
			Assertions.fail("An error has occured !");
			e.printStackTrace();
		}        
        
        
        
    }
	
	@Test
    public void testGetPerson() {	
			
		Person existingPerson = personRepo.findByLastNameAndFirstName(getDefaultPerson().getLastName(), getDefaultPerson().getFirstName());
		
		Assertions.assertNull(existingPerson);
			
		
			try {
				
				personService.createPerson(getDefaultPerson());
				
				
				
				Person personCreated = personRepo.findByLastNameAndFirstName(getDefaultPerson().getLastName(), getDefaultPerson().getFirstName());
				Assertions.assertNotNull(personCreated);				
				
				
				Assertions.assertEquals(getDefaultPerson().getFirstName(), personCreated.getFirstName());
			    Assertions.assertEquals(getDefaultPerson().getLastName(), personCreated.getLastName());				 
					 
						
				
			} catch (Exception e) {
				Assertions.fail("An error has occured !");
				e.printStackTrace();
			}
			       
        
        
    }
	
	
	@Test
    public void testGetFirestation() {	
			
		Firestation existingFirestatiton = firestationRepo.findByAddress(getDefaultFirestation().getAddress());
		
		Assertions.assertNull(existingFirestatiton);
			
		
			try {
				
				firestationService.createFirestationMapping(getDefaultFirestation());
				
				
				
				Firestation firestationCreated = firestationRepo.findByAddress(getDefaultFirestation().getAddress());
				Assertions.assertNotNull(firestationCreated);				
				
				
				Assertions.assertEquals(getDefaultFirestation().getAddress(), firestationCreated.getAddress());
			    Assertions.assertEquals(getDefaultFirestation().getFirestationNumber(), firestationCreated.getFirestationNumber());				 
					 
						
				
			} catch (Exception e) {
				Assertions.fail("An error has occured !");
				e.printStackTrace();
			}
			       
        
        
    }
	
	
	@Test
    public void testGetMedicalRecord() {	
			
		MedicalRecord existingMedicalRecord = medicalRecordRepo.findByFirstNameAndLastName(getDefaultMedicalRecord().getFirstName(), getDefaultMedicalRecord().getLastName());
		
		Assertions.assertNull(existingMedicalRecord);
			
		
			try {
				
				medicalRecordService.createMedicalRecord(getDefaultMedicalRecord());
				
				
				
				MedicalRecord medicalRecordCreated = medicalRecordRepo.findByFirstNameAndLastName(getDefaultMedicalRecord().getFirstName(), getDefaultMedicalRecord().getLastName());
				Assertions.assertNotNull(medicalRecordCreated);				
				
				
				Assertions.assertEquals(getDefaultMedicalRecord().getFirstName(), medicalRecordCreated.getFirstName());
			    Assertions.assertEquals(getDefaultMedicalRecord().getLastName(), medicalRecordCreated.getLastName());			 
					 
						
				
			} catch (Exception e) {
				Assertions.fail("An error has occured !");
				e.printStackTrace();
			}
			       
        
        
    }
	
	
	@Test
    public void testUpdatePerson() {
		
		Person person = getDefaultPerson();
		
		try {
			Person personCreated = personService.createPerson(person);
			
			if(personCreated != null) {
				person = personCreated;
			}else {
				 person = personRepo.findByLastNameAndFirstName(getDefaultPerson().getLastName(), getDefaultPerson().getFirstName());
				 Assertions.assertNotNull(person);
			}
			Assertions.assertNotEquals("41000", person.getZip());
			person.setZip("41000");			
			person = personService.updatePerson(person);
			Assertions.assertEquals("41000", person.getZip());
			person = personRepo.findByLastNameAndFirstName(getDefaultPerson().getLastName(), getDefaultPerson().getFirstName());
			Assertions.assertEquals("41000", person.getZip());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			               
        
        
    }
	
	@Test
    public void testUpdateFirestation() {
		
		Firestation firestation = getDefaultFirestation();
        
		
		try {
			Firestation firestationCreated = firestationService.createFirestationMapping(firestation);
			
			if(firestationCreated != null) {
				firestation = firestationCreated;
			}else {
				firestation = firestationRepo.findByAddress(getDefaultFirestation().getAddress());				 
			}
			
			firestation.setFirestationNumber(2);			
			firestation = firestationService.updateFirestationMappingByAddress(firestation.getAddress(), firestation);			
			Assertions.assertEquals(2, firestation.getFirestationNumber());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			               
        
        
    }
	
	
	@Test
    public void testUpdateMedicalRecord() {
		
		MedicalRecord medicalRecord = getDefaultMedicalRecord();
        
		
		try {
			MedicalRecord medicalRecordCreated = medicalRecordService.createMedicalRecord(medicalRecord);
			
			if(medicalRecord != null) {
				medicalRecord = medicalRecordCreated;
			}else {
				medicalRecord = medicalRecordRepo.findByFirstNameAndLastName(getDefaultMedicalRecord().getFirstName(), getDefaultMedicalRecord().getLastName());				 
			}
			
			medicalRecord.setFirstName("bob");			
			medicalRecord = medicalRecordService.updateMedicalRecordByFirstNameAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord);			
			Assertions.assertEquals("bob", medicalRecord.getFirstName());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			               
        
        
    }
	
	
	
	
	
	@Test
    public void testDeletePerson() {
		
		Person person = new Person(getDefaultPerson().getFirstName(), getDefaultPerson().getLastName(), "johny@doe.fr", "25", "15/11/1998", "0657863214", "4 rue de la rue", "le mans", "72000");
        
		
		try {
			Person personCreated = personService.createPerson(person);
			
			if(personCreated != null) {
				person = personCreated;
			}else {
				 person = personRepo.findByLastNameAndFirstName(getDefaultPerson().getLastName(), getDefaultPerson().getFirstName());				 
			}
			
			personRepo.deleteById(person.getId());
			person = personRepo.findByLastNameAndFirstName(getDefaultPerson().getLastName(), getDefaultPerson().getFirstName());
			Assertions.assertNull(person);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		               
        
        
    }
	
	
	@Test
    public void testDeleteFirestation() {
		
		Firestation firestation = getDefaultFirestation();
		
		try {
			Firestation firestationCreated = firestationService.createFirestationMapping(firestation);
			
			if(firestationCreated != null) {
				firestation = firestationCreated;
			}else {
				firestation = firestationService.findFirestationMappingByAddress(getDefaultFirestation().getAddress());
			}
			
			firestationService.deleteFirestationMappingById(firestation.getId());
			firestation = firestationService.findFirestationMappingByAddress(getDefaultFirestation().getAddress());
			Assertions.assertNull(firestation);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		               
        
        
    }
	
	
	@Test
    public void testDeleteMedicalRecord() {
		
		MedicalRecord medicalRecord = getDefaultMedicalRecord();
		
		try {
			MedicalRecord medicalRecordCreated = medicalRecordService.createMedicalRecord(medicalRecord);
			
			if(medicalRecordCreated != null) {
				medicalRecord = medicalRecordCreated;
			}else {
				medicalRecord = medicalRecordService.findMedicalRecordByFirstNameAndLastName(getDefaultMedicalRecord().getFirstName(), getDefaultMedicalRecord().getLastName());
			}
			
			medicalRecordService.deleteMedicalRecordById(medicalRecord.getId());
			medicalRecord = medicalRecordService.findMedicalRecordByFirstNameAndLastName(getDefaultMedicalRecord().getFirstName(), getDefaultMedicalRecord().getLastName());
			Assertions.assertNull(medicalRecord);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		               
        
        
    }
	

}
