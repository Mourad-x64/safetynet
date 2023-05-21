package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.safetynet.dao.FirestationRepository;
import com.openclassrooms.safetynet.model.Firestation;

public class FirestationServiceImpl implements FirestationService {

    @Autowired
    private FirestationRepository dao;
    

	@Override
	public Firestation createFirestationMapping(Firestation firestation) throws Exception {
		Optional<Firestation> existingFirestationMapping = dao.findById(firestation.getId());
    	if(existingFirestationMapping != null) {
    		throw new Exception("this Mapping already exists in the database.");
    	}else {
			firestation.setId(UUID.randomUUID().toString());
			
			return dao.save(firestation);
		}
	}


	@Override
	public Collection<Firestation> getAllFirestationMappings() {
		return dao.findAll();
	}	


	@Override
	public Firestation updateFirestationMappingById(Firestation firestation) {
		Optional<Firestation> existingFirestationMapping = dao.findById(firestation.getId());
    	if(existingFirestationMapping == null) {
    		firestation.setId(UUID.randomUUID().toString());
    	}else {
			firestation.setId(existingFirestationMapping.get().getId());
		}
    	
    	return dao.save(firestation);    
	}
	
	
	@Override
	public Firestation updateFirestationMappingByAddress(String address, Firestation firestation) {
		Firestation existingFirestationMapping = dao.findByAddress(address);
    	if(existingFirestationMapping == null) {
    		firestation.setId(UUID.randomUUID().toString());
    	}else {
			firestation.setId(existingFirestationMapping.getId());
		}
    	
    	return dao.save(firestation);    
	}


	@Override
	public void deleteAllFirestationMappings() {
		dao.deleteAll();
		
	}


	@Override
	public Firestation findFirestationMappingByFirestationNumber(int firestationNumber) {
		
		return dao.findByFirestationNumber(firestationNumber);
	}


	@Override
	public Firestation findFirestationMappingByAddress(String address) {
		
		return dao.findByAddress(address);
	}
	
	@Override
	public Optional<Firestation> findFirestationMappingById(String id) {
		
		return dao.findById(id);
	}


	@Override
	public void deleteFirestationMappingByFirestationNumber(int firestationNumber) {
		dao.deleteByFirestationNumber(firestationNumber);
		
	}


	@Override
	public void deleteFirestationMappingByAddress(String address) {
		dao.deleteByAddress(address);
		
	}	


	@Override
	public void deleteFirestationMappingById(String id) {
		dao.deleteById(id);
		
	}


	
}
