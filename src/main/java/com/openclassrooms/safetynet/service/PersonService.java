package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.openclassrooms.safetynet.model.Person;

public interface PersonService {



    public void createPerson(List<Person> emp);


    public Collection<Person> getAllPersons();


    public Optional<Person> findPersonById(int id);


    public void deletePersonById(int id);


    public void updatePerson(Person person);


    public void deleteAllPersons();
}
