package com.kiran.hospital.dto;

import com.kiran.hospital.entity.type.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupResponseDto {
	
	private Long id;
	private String username;
	private RoleType role;
	
}
