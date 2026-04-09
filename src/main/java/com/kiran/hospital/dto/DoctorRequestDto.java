package com.kiran.hospital.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorRequestDto {
	
	@NotBlank(message = "name is required")
    private String name;
	
	@NotBlank(message = "email is required")
	@Email(message = "Email is invalid")
    private String email;
	
	@NotBlank(message = "specialization is required")
    private String specialization;
}
