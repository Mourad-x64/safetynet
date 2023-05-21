package com.openclassrooms.safetynet.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.openclassrooms.safetynet.model.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {

	public MedicalRecord findByFirstNameAndLastName(String firstName, String LastName);	
	public void deleteByFirstNameAndLastName(String firstName, String LastName);	
	public MedicalRecord updateByFirstNameAndLastName(String firstName, String LastName, MedicalRecord medicalRecord);
		

}
