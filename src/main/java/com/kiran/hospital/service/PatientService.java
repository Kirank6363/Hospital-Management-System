package com.kiran.hospital.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kiran.hospital.dto.PatientRequestDto;
import com.kiran.hospital.dto.PatientResponseDto;
import com.kiran.hospital.entity.Patient;
import com.kiran.hospital.entity.User;
import com.kiran.hospital.error.ResourceNotFoundException;
import com.kiran.hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PatientService {
	
	private final PatientRepository patientRepository;
	private final ModelMapper modelMapper;
	
	public PatientResponseDto createPatient(PatientRequestDto dto) {
		
		if(patientRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Patient already exists with email "+dto.getEmail());
		}
		
		Patient patient = modelMapper.map(dto, Patient.class);
        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientResponseDto.class);
	}
	
	public List<PatientResponseDto> getAllPatients(int page, int size, String sortBy, String direction) {
		
		Sort sort = direction.equalsIgnoreCase("desc")
				 ? Sort.by(sortBy).descending()
				 : Sort.by(sortBy).ascending();
		
		Pageable pageable= PageRequest.of(page, size, sort);
		 return patientRepository.findAll(pageable)
				 .stream()
				 .map(patient-> modelMapper.map(patient, PatientResponseDto.class))
				.toList();
		 
	}
	
	public PatientResponseDto getPatientById(Long id) {
		Patient patient =  patientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient not found with id "+id));      
		return patient == null ? null : modelMapper.map(patient, PatientResponseDto.class);
	}
	
	public void deletePatientById(Long id) {
		patientRepository.deleteById(id);
	}
	
	public PatientResponseDto updatePatientById(Long id, PatientRequestDto dto){
		Patient patient = patientRepository.findById(id)
									.orElseThrow(()->new ResourceNotFoundException("Patient not found with id "+id));
		
		if(patientRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
			throw new IllegalArgumentException("Patient already exists with email "+dto.getEmail());
		}
		
		patient.setName(dto.getName());
		patient.setEmail(dto.getEmail());
		patient.setGender(dto.getGender());
		patient.setBirthDate(dto.getBirthDate());
		
		patient = patientRepository.save(patient);
		
		return modelMapper.map(patient, PatientResponseDto.class);
		
	}
	
	public List<PatientResponseDto> searchPatientByName(String name){
		return patientRepository.findByNameContainingIgnoreCase(name)
				.stream()
				.map(patient -> modelMapper.map(patient, PatientResponseDto.class))
				.toList();
	}
	
	public PatientResponseDto getCurrentPatientProfile() {
	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    Patient patient = patientRepository.findByUserId(user.getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Patient profile not found for user id: " + user.getId()));

	    return modelMapper.map(patient, PatientResponseDto.class);
	}


}










