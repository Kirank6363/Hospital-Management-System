package com.kiran.hospital.dto;

import com.kiran.hospital.entity.type.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentUserResponseDto {
    private Long id;
    private String username;
    private RoleType role;
}
