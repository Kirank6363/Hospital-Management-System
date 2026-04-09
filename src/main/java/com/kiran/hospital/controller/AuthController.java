package com.kiran.hospital.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiran.hospital.dto.CurrentUserResponseDto;
import com.kiran.hospital.dto.LoginRequestDto;
import com.kiran.hospital.dto.LoginResponseDto;
import com.kiran.hospital.dto.SignupRequestDto;
import com.kiran.hospital.dto.SignupResponseDto;
import com.kiran.hospital.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	
	private final AuthService authService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<SignupResponseDto> signup( @Valid @RequestBody SignupRequestDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(dto));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto){
		return ResponseEntity.ok(authService.login(dto));
	}
	
	@GetMapping("/me")
	public ResponseEntity<CurrentUserResponseDto> getCurrentUser() {
	    return ResponseEntity.ok(authService.getCurrentUser());
	}

}
