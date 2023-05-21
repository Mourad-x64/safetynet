package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.openclassrooms.safetynet.model.Person;

public interface PersonService {



    public Person createPerson(Person person) throws Exception;


    public Collection<Person> getAllPersons();


    public Optional<Person> findPersonById(String id);


    public void deletePersonById(String id);


    public Person updatePerson(Person person);


    public void deleteAllPersons();


	public Person findByLastNameAndFirstName(String lastName, String firstName);
}
