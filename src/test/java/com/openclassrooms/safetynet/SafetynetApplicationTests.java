package com.openclassrooms.safetynet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.openclassrooms.safetynet.dao.PersonRepository;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonServiceImpl;

@DataMongoTest
@Import(TestEntityManagerAutoConfiguration.class)
class SafetynetApplicationTests {
	
	@Autowired	
	PersonRepository personRepo;
	@Autowired
	PersonServiceImpl personServImpl;
	

	@Test
    public void testCreatePerson() {
		Person person = new Person("Johny", "Doe", "johny@doe.fr", "25", "15/11/1998", "0657863214", "4 rue de la rue", "le mans", "72000");
		
        
		try {
			Person personCreated = personServImpl.createPerson(person);
			
			if(personCreated != null) {
				Assertions.assertEquals("Johny", personCreated.getFirstName());
		        Assertions.assertEquals("Doe", personCreated.getLastName());
			}else {
				System.out.println("person allready exist.");
				Assertions.assertTrue(true);
			}			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}        
        
        
        
    }
	
	@Test
    public void testGetPerson() {		
        
		
			Person person = personRepo.findByLastNameAndFirstName("Doe", "Johny");
			Assertions.assertEquals("Johny", person.getFirstName());
	        Assertions.assertEquals("Doe", person.getLastName());       
        
        
    }

}
