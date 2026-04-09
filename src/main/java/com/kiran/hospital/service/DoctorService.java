package com.kiran.hospital.service;


import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kiran.hospital.dto.DoctorRequestDto;
import com.kiran.hospital.dto.DoctorResponseDto;
import com.kiran.hospital.entity.Doctor;
import com.kiran.hospital.error.ResourceNotFoundException;
import com.kiran.hospital.repository.DoctorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public DoctorResponseDto createDoctor(DoctorRequestDto dto) {
    	
    	if(doctorRepository.existsByEmail(dto.getEmail())) {
    		throw new IllegalArgumentException("Doctor already exists with email "+dto.getEmail());
    	}
    	
        Doctor doctor = modelMapper.map(dto, Doctor.class);
        doctor = doctorRepository.save(doctor);
        return modelMapper.map(doctor, DoctorResponseDto.class);
    }

    public List<DoctorResponseDto> getAllDoctors(int page, int size, String sortBy, String direction) {
    	
    	Sort sort= direction.equalsIgnoreCase("desc")
    			? Sort.by(sortBy).descending()
    		    : Sort.by(sortBy).ascending();
    	
    	Pageable pageable = PageRequest.of(page, size, sort);
    	
        return doctorRepository.findAll(pageable)
                .stream()
                .map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
                .toList();
    }

    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Doctor not found with id "+id));       
        return doctor == null ? null : modelMapper.map(doctor, DoctorResponseDto.class);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
    
    
    public DoctorResponseDto updateDoctorById(Long id, DoctorRequestDto dto) {
    	
    	Doctor doctor= doctorRepository.findById(id)
    			.orElseThrow(()-> new ResourceNotFoundException("Doctor not found with id "+id));
    	
    	if(doctorRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
    		throw new IllegalArgumentException("Doctor already exists with email "+dto.getEmail());
    	}
    	
    	doctor.setName(dto.getName());
    	doctor.setEmail(dto.getEmail());
    	doctor.setSpecialization(dto.getSpecialization());
    	
    	doctor = doctorRepository.save(doctor);
    	
    	return modelMapper.map(doctor, DoctorResponseDto.class);
    }
    
    
    public List<DoctorResponseDto> searchDoctorByName(String name){
    	return doctorRepository.findByNameContainingIgnoreCase(name)
    			.stream()
    			.map(doctor -> modelMapper.map(doctor, DoctorResponseDto.class))
    			.toList();
    }
    
    
    
    
    
    
}
