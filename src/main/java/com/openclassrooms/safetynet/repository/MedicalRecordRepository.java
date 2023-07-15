package com.openclassrooms.safetynet.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.model.MedicalRecord;


@Repository
@Qualifier("medicalRecordService")
public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {

	public MedicalRecord findByFirstNameAndLastName(String firstName, String LastName);	
	public void deleteByFirstNameAndLastName(String firstName, String LastName);	

}
