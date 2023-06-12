package com.openclassrooms.safetynet.dao;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.safetynet.model.Firestation;


@Repository
@Qualifier("firestationService")
public interface FirestationRepository extends MongoRepository<Firestation, String> {

	public Firestation findByFirestationNumber (int firestationNumber);	
	public Firestation findByAddress(String address);
	public void deleteByFirestationNumber(int firestationNumber);
	public void deleteByAddress(String address);
		

}
