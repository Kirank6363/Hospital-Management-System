package com.kiran.hospital.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kiran.hospital.config.AppConfig;
import com.kiran.hospital.dto.PatientRequestDto;
import com.kiran.hospital.dto.PatientResponseDto;
import com.kiran.hospital.entity.Patient;
import com.kiran.hospital.service.PatientService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    

    private final PatientService patientService;

    

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto dto) {
        PatientResponseDto response =  patientService.createPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
    			@RequestParam(defaultValue = "1") int page,
    			@RequestParam(defaultValue = "5") int size,
    			@RequestParam(defaultValue = "id") String sortBy,
    			@RequestParam(defaultValue = "asc") String direction
    		) {
        return ResponseEntity.ok(patientService.getAllPatients(page-1, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatientById(@PathVariable Long id,@Valid @RequestBody PatientRequestDto dto){
    	return ResponseEntity.ok(patientService.updatePatientById(id, dto));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PatientResponseDto>> searchPatientByName(@RequestParam String name){
    	return ResponseEntity.ok(patientService.searchPatientByName(name));
    }
    
    @GetMapping("/me")
    public ResponseEntity<PatientResponseDto> getCurrentPatientProfile() {
        return ResponseEntity.ok(patientService.getCurrentPatientProfile());
    }

    
}








