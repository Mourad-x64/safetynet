package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;

import com.openclassrooms.safetynet.model.MedicalRecord;

public interface MedicalRecordService {



    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws Exception;


    public Collection<MedicalRecord> getAllMedicalRecords();


    public Optional<MedicalRecord> findMedicalRecordById(String id);
    
    
    public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String LastName);   


    public void deleteMedicalRecordByFirstNameAndLastName(String firstName, String LastName);    
    
    
    public void deleteMedicalRecordById(String id);


    public MedicalRecord updateMedicalRecordById(MedicalRecord medicalRecord);
    
    
    public MedicalRecord updateMedicalRecordByFirstNameAndLastName(String firstName, String LastName, MedicalRecord medicalRecord);


    public void deleteAllMedicalRecords();
	
}
