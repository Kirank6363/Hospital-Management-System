package com.kiran.hospital.dto;

import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {
	
	@NotBlank(message = "Name is required")
    private String name;
	
	@NotBlank(message  = "email is required")
	@Email(message = "invalid email")
    private String email;
	
	@NotBlank(message = "gender is required")
    private String gender;
    
    @NotNull(message= "birthdate is required")
    private LocalDate birthDate;
}














