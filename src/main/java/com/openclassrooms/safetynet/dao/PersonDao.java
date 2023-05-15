package com.openclassrooms.safetynet.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetynet.model.Person;

@Repository
public interface PersonDao extends MongoRepository<Person, Integer> {


}
