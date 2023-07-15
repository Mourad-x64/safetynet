package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.PersonRepository;

@Component
public class PersonServiceImpl implements PersonService {

    @Autowired
    @Qualifier("personService")
    PersonRepository repo;


    @Override
    public Person createPerson(Person person) throws Exception {
    	Person existingPerson = repo.findByLastNameAndFirstName(person.getLastName(), person.getFirstName());
    	if(existingPerson != null) {
    		throw new Exception("this person already exists in the database.");
    		
    	}else {
			person.setId(UUID.randomUUID().toString());
			
			return repo.save(person);
		}
            
    }


    @Override
    public Collection<Person> getAllPersons() {
        return repo.findAll();
    }


    @Override
    public Optional<Person> findPersonById(String id) {
        return repo.findById(id);
    }


    @Override
    public void deletePersonById(String id) {
        repo.deleteById(id);
    }


    @Override
    public Person updatePerson(Person person) {
    	Person existingPerson = repo.findByLastNameAndFirstName(person.getLastName(), person.getFirstName());
    	if(existingPerson == null) {
    		person.setId(UUID.randomUUID().toString());
    	}else {
			person.setId(existingPerson.getId());
		}
    	
    	return repo.save(person);    	
        
    }


    @Override
    public void deleteAllPersons() {
        repo.deleteAll();
    }


	@Override
	public Person findByLastNameAndFirstName(String lastName, String firstName) {
		
		return repo.findByLastNameAndFirstName(lastName, firstName);
	}
}
