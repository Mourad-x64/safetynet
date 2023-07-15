package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.repository.FirestationRepository;

@Component
public class FirestationServiceImpl implements FirestationService {

    @Autowired    
    private FirestationRepository repo;
    

	@Override
	public Firestation createFirestationMapping(Firestation firestation) throws Exception {
		Optional<Firestation> existingFirestationMapping = repo.findById(firestation.getId());
    	if(existingFirestationMapping.isPresent()) {
    		throw new Exception("this Mapping already exists in the database.");
    	}else {
			firestation.setId(UUID.randomUUID().toString());
			
			return repo.save(firestation);
		}
	}


	@Override
	public Collection<Firestation> getAllFirestationMappings() {
		return repo.findAll();
	}	


	@Override
	public Firestation updateFirestationMappingById(Firestation firestation) {
		Optional<Firestation> existingFirestationMapping = repo.findById(firestation.getId());
    	if(existingFirestationMapping.isEmpty()) {
    		firestation.setId(UUID.randomUUID().toString());
    	}else {
			firestation.setId(existingFirestationMapping.get().getId());
		}
    	
    	return repo.save(firestation);    
	}
	
	
	@Override
	public Firestation updateFirestationMappingByAddress(String address, Firestation firestation) {
		Firestation existingFirestationMapping = repo.findByAddress(address);
    	if(existingFirestationMapping == null) {
    		firestation.setId(UUID.randomUUID().toString());
    	}else {
			firestation.setId(existingFirestationMapping.getId());
		}
    	
    	return repo.save(firestation);    
	}


	@Override
	public void deleteAllFirestationMappings() {
		repo.deleteAll();
		
	}


	@Override
	public Firestation findFirestationMappingByFirestationNumber(int firestationNumber) {
		
		return repo.findByFirestationNumber(firestationNumber);
	}


	@Override
	public Firestation findFirestationMappingByAddress(String address) {
		
		return repo.findByAddress(address);
	}
	
	@Override
	public Optional<Firestation> findFirestationMappingById(String id) {
		
		return repo.findById(id);
	}


	@Override
	public void deleteFirestationMappingByFirestationNumber(int firestationNumber) {
		repo.deleteByFirestationNumber(firestationNumber);
		
	}


	@Override
	public void deleteFirestationMappingByAddress(String address) {
		repo.deleteByAddress(address);
		
	}	


	@Override
	public void deleteFirestationMappingById(String id) {
		repo.deleteById(id);
		
	}


	
}
