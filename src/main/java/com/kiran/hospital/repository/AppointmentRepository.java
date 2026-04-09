package com.kiran.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.hospital.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	
}