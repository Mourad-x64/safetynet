package com.openclassrooms.safetynet.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;


@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    @Qualifier(value = "PersonService")
    PersonService serv;

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * Method to fetch all users from the db.
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Person> getAll() {
        System.out.println("-------> : getAllUsers");
        //logger.debug("Getting all users.");
        return serv.getAllPersons();
    }

    /**
     * Method to fetch user by id.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable(value= "person-id") int id) {
        //logger.debug("Getting users with person-id= {}.", id);
        return serv.findPersonById(id);
    }

    /**
     * Method to update user by id.
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable(value= "id") int id, @RequestBody Person person) {
        //logger.debug("Updating user with user-id= {}.", id);
        person.setId(id);
        serv.updatePerson(person);
        return "user record for person-id= " + id + " updated.";
    }

    /**
     * Method to delete user by id.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable(value= "id") int id) {
        //logger.debug("Deleting user with user-id= {}.", id);
        serv.deletePersonById(id);
        return "user record for user-id= " + id + " deleted.";
    }

    /**
     * Method to delete all users from the db.
     * @return
     */
    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {
        //logger.debug("Deleting all users.");
        serv.deleteAllPersons();
        return "All users records deleted.";
    }

}