package com.openclassrooms.safetynet.controller;

import java.util.Collection;


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
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;


@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordController {

    @Autowired   
    private MedicalRecordService serv;
    
    @Autowired
	private DataManager dataManager; 

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    /**
     * Method to create medical record.
     * @param medicalRecord
     * @return
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        logger.debug("create a medical record.");        
        try {
			serv.createMedicalRecord(medicalRecord);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
        return "Medical record created.";
    }

    /**
     * Method to fetch all medical records from the db.
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<MedicalRecord> getAllMedicalRecords() {        
        logger.debug("Getting all medical records.");
        return serv.getAllMedicalRecords();
    }

    /**
     * Method to fetch medical records by firstname and lastname .
     * @param firstName
     * @param lastName
     * @return
     */
    @GetMapping("/{firstName}/{lastName}")
    public MedicalRecord getMedicalRecordByFirstNameAndLastname(@PathVariable String firstName, @PathVariable String LastName) {
        logger.debug("Getting medical record for firstname {} lastname {}.", firstName, LastName);
        return serv.findMedicalRecordByFirstNameAndLastName(firstName, LastName);
    }    
    
    
    /**
     * Method to update medical record by firstname and lastname.
     * @param firstName
     * @param lastName
     * @param medicalRecord
     * @return
     */
    @PutMapping("/update/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public String updateMedicalRecord(@PathVariable String firstName, @PathVariable String lastName, @RequestBody MedicalRecord medicalRecord) {
        logger.debug("Updating medical record for {} {}.", firstName, lastName);        
        serv.updateMedicalRecordByFirstNameAndLastName(firstName, lastName, medicalRecord);
        return "Medical record for "+firstName+" "+lastName+" updated.";
    }

        
    /**
     * Method to delete person by firstname and lastname.
     * @param firstName
     * @param lastName
     * @return       
     */
    @DeleteMapping("/delete/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {    	       
        serv.deleteMedicalRecordByFirstNameAndLastName(firstName, lastName);
        logger.debug("Deleting medical record for {} {}.", firstName, lastName);
        return "Medical record deleted.";
    }   
    

    /**
     * Method to delete all medical records from the db.
     * @return
     */
    @DeleteMapping(value= "/deleteAll")
    public String deleteAllMedicalRecords() {
        logger.debug("Deleting all medical records.");
        serv.deleteAllMedicalRecords();
        return "All medical records deleted.";
    }

}