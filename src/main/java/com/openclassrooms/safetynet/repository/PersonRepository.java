package com.openclassrooms.safetynet.repository;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.model.Person;


@Repository
@Qualifier("personService")
public interface PersonRepository extends MongoRepository<Person, String> {
	public Person findByLastNameAndFirstName(String lastName, String FirstName);
	public Optional<Person> findById(String id);
		
	

}
