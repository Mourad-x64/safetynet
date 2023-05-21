package com.openclassrooms.safetynet.controller;

import java.util.Collection;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.service.FirestationService;


@RestController
@RequestMapping("/firestationMappings")
public class FirestationController {

    @Autowired    
    private FirestationService serv;
    
    @Autowired
	private DataManager dataManager; 

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    /**
     * Method to create firestation Mapping.
     * @param firestation
     * @return
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String createFirestationMapping(@RequestBody Firestation firestation) {
        logger.debug("create a firestation mapping.");        
        try {
			serv.createFirestationMapping(firestation);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
        return "Firestation mapping created.";
    }

    /**
     * Method to fetch all firestation mappings from the db.
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<Firestation> getAllFirestationMappings() {        
        logger.debug("Getting all Firestation Mappings.");
        return serv.getAllFirestationMappings();
    }

    /**
     * Method to fetch firestation mapping by firestation number.
     * @param firestationNumber
     * @return
     */
    @GetMapping("/{firestationNumber}")
    public Firestation getFirestationMappingByFirestationNumber(@PathVariable int firestationNumber) {
        logger.debug("Getting firestation mapping for firestation number {}.", firestationNumber);
        return serv.findFirestationMappingByFirestationNumber(firestationNumber);
    }
    
    /**
     * Method to fetch firestation mapping by address.
     * @param address
     * @return
     */
    @GetMapping("/{address}")
    public Firestation getFirestationMappingByAdress(@PathVariable String address) {
        logger.debug("Getting firestation mapping for firestation address {}.", address);
        return serv.findFirestationMappingByAddress(address);
    }
    
    
    /**
     * Method to update firestation mapping by address.
     * @param address
     * @param firestation
     * @return
     */
    @PutMapping("/updateByAddress/{address}")
    @ResponseStatus(HttpStatus.OK)
    public String updateFirestationMappingByAdress(@PathVariable String address, @RequestBody Firestation firestation) {
        logger.debug("Updating firestation mapping with address = {}.", address);        
        serv.updateFirestationMappingByAddress(address, firestation);
        return "firestation record for address= " + address + " updated.";
    }

        
    /**
     * Method to delete person by firestation number.
     * @param firestationNumber
     * @return       
     */
    @DeleteMapping("/deleteByFirestationNumber/{firestationNumber}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteFirestationMappingByFirestationNumnber(@PathVariable int firestationNumber) {    	       
        serv.deleteFirestationMappingByFirestationNumber(firestationNumber);
        logger.debug("Deleting firestation mapping with firestation number= {}.", firestationNumber);
        return "firestation mapping record deleted.";
    }
    
    
    /**
     * Method to delete person by address.
     * @param address
     * @return     
     */
    @DeleteMapping("/deleteByAddress/{address}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteFirestationMappingByAddress(@PathVariable String address) {    	       
        serv.deleteFirestationMappingByAddress(address);
        logger.debug("Deleting firestation mapping with address = {}.", address);
        return "firestation mapping record deleted.";
    }
    

    /**
     * Method to delete all firestation mappings from the db.
     * @return
     */
    @DeleteMapping(value= "/deleteAll")
    public String deleteAllFirestationMappings() {
        logger.debug("Deleting all firestation mappings.");
        serv.deleteAllFirestationMappings();
        return "All firestation mapping records deleted.";
    }

}