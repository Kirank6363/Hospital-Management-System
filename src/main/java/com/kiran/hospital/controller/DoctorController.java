package com.kiran.hospital.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.kiran.hospital.dto.DoctorRequestDto;
import com.kiran.hospital.dto.DoctorResponseDto;
import com.kiran.hospital.entity.Doctor;
import com.kiran.hospital.service.DoctorService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@Valid @RequestBody DoctorRequestDto doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.createDoctor(doctor));
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(
    			@RequestParam(defaultValue = "0") int page,
    			@RequestParam(defaultValue = "5") int size,
    			@RequestParam(defaultValue = "id") String sortBy,
    			@RequestParam(defaultValue = "asc") String direction
    		) {
        return ResponseEntity.ok(doctorService.getAllDoctors(page, size, sortBy, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctorById(@PathVariable Long id, @Valid @RequestBody DoctorRequestDto dto){
    	return ResponseEntity.ok(doctorService.updateDoctorById(id, dto));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<DoctorResponseDto>> searchDoctorByName(@RequestParam String name){
    	return ResponseEntity.ok(doctorService.searchDoctorByName(name));
    }
    
    
    
    
    
}
