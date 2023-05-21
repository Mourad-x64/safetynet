package com.openclassrooms.safetynet.dao;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetynet.model.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> {
	public Person findByLastNameAndFirstName(String lastName, String FirstName);
	public Optional<Person> findById(String id);
		
	

}