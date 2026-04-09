package com.kiran.hospital.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.hospital.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	List<Patient> findByNameContainingIgnoreCase(String name);
	
	boolean existsByEmail(String email);
	
	boolean existsByEmailAndIdNot(String email, Long id);
	
	Optional<Patient> findByUserId(Long userId);

}
