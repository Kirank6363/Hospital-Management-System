package com.kiran.hospital.dto;

import com.kiran.hospital.entity.type.RoleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
	
	@NotBlank(message = "username is required")
	private String username;
	
	@NotBlank(message = "password is required")
	private String password;
	
	@NotNull(message = "Role cannot be null")
	private RoleType role;
}
