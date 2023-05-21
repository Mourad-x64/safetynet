package com.openclassrooms.safetynet.service;

import java.util.Collection;
import java.util.Optional;

import com.openclassrooms.safetynet.model.Firestation;

public interface FirestationService {



    public Firestation createFirestationMapping(Firestation firestation) throws Exception;


    public Collection<Firestation> getAllFirestationMappings();


    public Optional<Firestation> findFirestationMappingById(String id);
    
    
    public Firestation findFirestationMappingByFirestationNumber(int firestationNumber);
    
    
    public Firestation findFirestationMappingByAddress(String address);


    public void deleteFirestationMappingByFirestationNumber(int firestationNumber);
    
    
    public void deleteFirestationMappingByAddress(String address);
    
    
    public void deleteFirestationMappingById(String id);


    public Firestation updateFirestationMappingById(Firestation firestation);
    
    
    public Firestation updateFirestationMappingByAddress(String address ,Firestation firestation);


    public void deleteAllFirestationMappings();
	
}
