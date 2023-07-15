package com.openclassrooms.safetynet.controller;

import java.util.Collection;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.safetynet.domain.DataManager;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.response.FireResponse;
import com.openclassrooms.safetynet.service.PersonService;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired    
    PersonService serv;
    
    @Autowired
	private DataManager dataManager; 

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    /**
     * Method to create persons.
     * @param persons
     * @return
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String create(@RequestBody Person person) {
        logger.info("create a person.");        
        try {
			serv.createPerson(person);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
        return "person created.";
    }

    /**
     * Method to fetch all persons from the db.
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Person> getAll() {
        System.out.println("-------> : getAllPersons");
        logger.info("Getting all persons.");
        return serv.getAllPersons();
    }

    /**
     * Method to fetch person by firstname and lastname.
     * @param id
     * @return
     */
    @GetMapping("/{firstName}/{lastName}")
    public Person getByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("Getting persons with firstname {} and lastname {}.",firstName, lastName );
        return serv.findByLastNameAndFirstName(lastName, firstName);
    }

    /**
     * Method to update person by id.
     * @param id
     * @param person
     * @return
     */
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable(value= "id") String id, @RequestBody Person person) {
        logger.info("Updating person with person-id= {}.", id);
        person.setId(id);
        serv.updatePerson(person);
        return "user record for person-id= " + id + " updated.";
    }

    /**
     * Method to delete person by id.
     * @param id
     * @return
     * @throws JSONException 
     */
    @DeleteMapping("/delete/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable String firstName, @PathVariable String lastName) throws JSONException {
    	String id = serv.findByLastNameAndFirstName(lastName, firstName).getId();        
        serv.deletePersonById(id);
        logger.info("Deleting person "+lastName+" "+firstName+" with person-id= {}.", id);
        return "person record for "+lastName+" "+firstName+" deleted.";
    }

    /**
     * Method to delete all persons from the db.
     * @return
     */
    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {
        logger.info("Deleting all persons.");
        serv.deleteAllPersons();
        return "All persons records deleted.";
    }

}