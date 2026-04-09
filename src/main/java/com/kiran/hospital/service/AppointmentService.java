package com.kiran.hospital.service;


import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kiran.hospital.dto.AppointmentRequestDto;
import com.kiran.hospital.dto.AppointmentResponseDto;
import com.kiran.hospital.entity.Appointment;
import com.kiran.hospital.entity.Doctor;
import com.kiran.hospital.entity.Patient;
import com.kiran.hospital.error.ResourceNotFoundException;
import com.kiran.hospital.repository.AppointmentRepository;
import com.kiran.hospital.repository.DoctorRepository;
import com.kiran.hospital.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    
    public AppointmentResponseDto createAppointment(AppointmentRequestDto dto) {
    	
    	if(dto.getAppointmentTime().isBefore(LocalDateTime.now())) {
    		throw new IllegalArgumentException("Appointment time cannot be scheduled in the past");
    	}
    	
    	Patient patient = patientRepository.findById(dto.getPatientId())
    	        .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + dto.getPatientId()));

    	Doctor doctor = doctorRepository.findById(dto.getDoctorId())
    	        .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + dto.getDoctorId()));


        if (patient == null || doctor == null) {
            return null;
        }

        Appointment appointment = Appointment.builder()
                .appointmentTime(dto.getAppointmentTime())
                .reason(dto.getReason())
                .build();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointment = appointmentRepository.save(appointment);

        return mapToResponseDto(appointment);
    }

    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public AppointmentResponseDto getAppointmentById(Long id) {
    	Appointment appointment = appointmentRepository.findById(id)
    	        .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));

    	return mapToResponseDto(appointment);

    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
    
    private AppointmentResponseDto mapToResponseDto(Appointment appointment) {
        AppointmentResponseDto responseDto = modelMapper.map(appointment, AppointmentResponseDto.class);
        responseDto.setPatientId(appointment.getPatient().getId());
        responseDto.setPatientName(appointment.getPatient().getName());
        responseDto.setDoctorId(appointment.getDoctor().getId());
        responseDto.setDoctorName(appointment.getDoctor().getName());
        return responseDto;
    }
    
    
    public AppointmentResponseDto updateAppointmentById(Long id, AppointmentRequestDto dto) {
    	
    	if (dto.getAppointmentTime().isBefore(LocalDateTime.now())) {
    	    throw new IllegalArgumentException("Appointment time cannot be in the past");
    	}

    	
    	Appointment appointment= appointmentRepository.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Appointment not found with id "+id));
    	
    	Patient patient=  patientRepository.findById(dto.getPatientId())
    								.orElseThrow(()-> new ResourceNotFoundException("Patient not found with id "+id));
    	Doctor doctor=  doctorRepository.findById(dto.getDoctorId())
		.orElseThrow(()-> new ResourceNotFoundException("Doctor not found with id "+id));
    	
    	
    	appointment.setPatient(patient);
    	appointment.setDoctor(doctor);
    	appointment.setReason(dto.getReason());
    	appointment.setAppointmentTime(dto.getAppointmentTime());
    	
    	appointment = appointmentRepository.save(appointment);
    	
    	return modelMapper.map(appointment, AppointmentResponseDto.class);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
}
