package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynet.dao.PersonDao;
import com.openclassrooms.safetynet.model.Person;

public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao dao;


    @Override
    public void createPerson(List<Person> user) {
        dao.saveAll(user);
    }


    @Override
    public Collection<Person> getAllPersons() {
        return dao.findAll();
    }


    @Override
    public Optional<Person> findPersonById(int id) {
        return dao.findById(id);
    }


    @Override
    public void deletePersonById(int id) {
        dao.deleteById(id);
    }


    @Override
    public void updatePerson(Person emp) {
        dao.save(emp);
    }


    @Override
    public void deleteAllPersons() {
        dao.deleteAll();
    }
}
