package com.openclassrooms.safetynet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.openclassrooms.safetynet.service.FirestationService;
import com.openclassrooms.safetynet.service.FirestationServiceImpl;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.MedicalRecordServiceImpl;
import com.openclassrooms.safetynet.service.PersonService;
import com.openclassrooms.safetynet.service.PersonServiceImpl;

@Configuration
@ComponentScan("com.openclassrooms.safetynet")
public class ApplicationConfig {

    

}