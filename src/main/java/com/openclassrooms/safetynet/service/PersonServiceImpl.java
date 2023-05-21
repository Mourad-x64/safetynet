package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynet.dao.PersonRepository;
import com.openclassrooms.safetynet.model.Person;

public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository dao;


    @Override
    public Person createPerson(Person person) throws Exception {
    	Person existingPerson = dao.findByLastNameAndFirstName(person.getLastName(), person.getFirstName());
    	if(existingPerson != null) {
    		throw new Exception("this person already exists in the database.");
    	}else {
			person.setId(UUID.randomUUID().toString());
			
			return dao.save(person);
		}
            
    }


    @Override
    public Collection<Person> getAllPersons() {
        return dao.findAll();
    }


    @Override
    public Optional<Person> findPersonById(String id) {
        return dao.findById(id);
    }


    @Override
    public void deletePersonById(String id) {
        dao.deleteById(id);
    }


    @Override
    public Person updatePerson(Person person) {
    	Person existingPerson = dao.findByLastNameAndFirstName(person.getLastName(), person.getFirstName());
    	if(existingPerson == null) {
    		person.setId(UUID.randomUUID().toString());
    	}else {
			person.setId(existingPerson.getId());
		}
    	
    	return dao.save(person);    	
        
    }


    @Override
    public void deleteAllPersons() {
        dao.deleteAll();
    }


	@Override
	public Person findByLastNameAndFirstName(String lastName, String firstName) {
		
		return dao.findByLastNameAndFirstName(lastName, firstName);
	}
}
