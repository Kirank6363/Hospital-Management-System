package com.kiran.hospital.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kiran.hospital.dto.CurrentUserResponseDto;
import com.kiran.hospital.dto.LoginRequestDto;
import com.kiran.hospital.dto.LoginResponseDto;
import com.kiran.hospital.dto.SignupRequestDto;
import com.kiran.hospital.dto.SignupResponseDto;
import com.kiran.hospital.entity.Doctor;
import com.kiran.hospital.entity.Patient;
import com.kiran.hospital.entity.User;
import com.kiran.hospital.entity.type.RoleType;
import com.kiran.hospital.repository.DoctorRepository;
import com.kiran.hospital.repository.PatientRepository;
import com.kiran.hospital.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final PatientRepository patientRepository;
	private final DoctorRepository  doctorRepository;

	
	public SignupResponseDto signUp(SignupRequestDto dto) {
		
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new IllegalArgumentException("Username already exists");
		}
		
		User user= User.builder()
			.username(dto.getUsername())
			.password(passwordEncoder.encode(dto.getPassword()))
			.role(dto.getRole())
			.build();
		
		user =userRepository.save(user);
		
		if (dto.getRole() == RoleType.PATIENT) {
	        Patient patient = Patient.builder()
	                .name(dto.getUsername())
	                .email(dto.getUsername())
	                .user(user)
	                .build();

	        patientRepository.save(patient);
	    }

	    if (dto.getRole() == RoleType.DOCTOR) {
	        Doctor doctor = Doctor.builder()
	                .name(dto.getUsername())
	                .email(dto.getUsername())
	                .specialization("General")
	                .user(user)
	                .build();

	        doctorRepository.save(doctor);
	    }
		
		return SignupResponseDto.builder()
				.id(user.getId())
				.username(user.getUsername())
				.role(user.getRole())
				.build();
	}
	
	
	public LoginResponseDto login(LoginRequestDto dto) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
				);
		
		User user =(User) authentication.getPrincipal();
		String token= jwtService.generateToken(user);
		
		return LoginResponseDto.builder()
			.message("Login Successfull!")
			.username(user.getUsername())
			.token(token)
			.build();
		
	}
	
	
	public CurrentUserResponseDto getCurrentUser() {
	    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    return CurrentUserResponseDto.builder()
	            .id(user.getId())
	            .username(user.getUsername())
	            .role(user.getRole())
	            .build();
	}

	
	
	
	
	
	
	
	
	
	
	

}
