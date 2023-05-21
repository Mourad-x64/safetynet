package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynet.dao.MedicalRecordRepository;
import com.openclassrooms.safetynet.model.MedicalRecord;

public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository dao;
	

	@Override
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) throws Exception {
		Optional<MedicalRecord> existingMedicalRecord = dao.findById(medicalRecord.getId());
    	if(existingMedicalRecord != null) {
    		throw new Exception("this Mapping already exists in the database.");
    	}else {
    		medicalRecord.setId(UUID.randomUUID().toString());
			
			return dao.save(medicalRecord);
		}
	}


	@Override
	public Collection<MedicalRecord> getAllMedicalRecords() {
		return dao.findAll();
	}


	@Override
	public Optional<MedicalRecord> findMedicalRecordById(String id) {
		return dao.findById(id);
	}


	@Override
	public MedicalRecord findMedicalRecordByFirstNameAndLastName(String firstName, String LastName) {
		return dao.findByFirstNameAndLastName(firstName, LastName);
	}


	@Override
	public void deleteMedicalRecordByFirstNameAndLastName(String firstName, String LastName) {
		dao.deleteByFirstNameAndLastName(firstName, LastName);
		
	}


	@Override
	public void deleteMedicalRecordById(String id) {
		dao.deleteById(id);
		
	}


	@Override
	public MedicalRecord updateMedicalRecordById(MedicalRecord medicalRecord) {
		Optional<MedicalRecord> existingMedicalRecord = dao.findById(medicalRecord.getId());
    	if(existingMedicalRecord == null) {
    		medicalRecord.setId(UUID.randomUUID().toString());
    	}else {
    		medicalRecord.setId(existingMedicalRecord.get().getId());
		}
    	
    	return dao.save(medicalRecord);
	}


	@Override
	public MedicalRecord updateMedicalRecordByFirstNameAndLastName(String firstName, String LastName, MedicalRecord medicalRecord) {
		MedicalRecord existingMedicalRecord = dao.findByFirstNameAndLastName(firstName, LastName);
    	if(existingMedicalRecord == null) {
    		medicalRecord.setId(UUID.randomUUID().toString());
    	}else {
    		medicalRecord.setId(existingMedicalRecord.getId());
		}
    	
    	return dao.save(medicalRecord);
	}


	@Override
	public void deleteAllMedicalRecords() {
		dao.deleteAll();		
	}


	
}
