package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.repository.MedicalRecordRepository;

@Component
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    @Qualifier("medicalRecordService")
    private MedicalRecordRepository repo;
	

	@Override
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		Optional<MedicalRecord> existingMedicalRecord = repo.findById(medicalRecord.getId());
    	if(existingMedicalRecord.isPresent()) {
    		throw new Exception("this Mapping already exists in the database.");
    	}else {
    		medicalRecord.setId(UUID.randomUUID().toString());
			
			return repo.save(medicalRecord);
		}
	}


	@Override
	public Collection<MedicalRecord> getAllMedicalRecords() {
		return repo.findAll();
	}


	@Override
	public Optional<MedicalRecord> findMedicalRecordById(String id) {
		return repo.findById(id);
	}


	@Override
	public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String LastName) {
		return repo.findByFirstNameAndLastName(firstName, LastName);
	}


	@Override
	public void deleteMedicalRecordByFirstNameAndLastName(String firstName, String LastName) {
		repo.deleteByFirstNameAndLastName(firstName, LastName);
		
	}


	@Override
	public void deleteMedicalRecordById(String id) {
		repo.deleteById(id);
		
	}


	@Override
	public MedicalRecord updateMedicalRecordById(MedicalRecord medicalRecord) {
		Optional<MedicalRecord> existingMedicalRecord = repo.findById(medicalRecord.getId());
    	if(existingMedicalRecord.isEmpty()) {
    		medicalRecord.setId(UUID.randomUUID().toString());
    	}else {
    		medicalRecord.setId(existingMedicalRecord.get().getId());
		}
    	
    	return repo.save(medicalRecord);
	}


	@Override
	public MedicalRecord updateMedicalRecordByFirstNameAndLastName(String firstName, String LastName, MedicalRecord medicalRecord) {
		MedicalRecord existingMedicalRecord = repo.findByFirstNameAndLastName(firstName, LastName);
    	if(existingMedicalRecord == null) {
    		medicalRecord.setId(UUID.randomUUID().toString());
    	}else {
    		medicalRecord.setId(existingMedicalRecord.getId());
		}
    	
    	return repo.save(medicalRecord);
	}


	@Override
	public void deleteAllMedicalRecords() {
		repo.deleteAll();		
	}


	
}
