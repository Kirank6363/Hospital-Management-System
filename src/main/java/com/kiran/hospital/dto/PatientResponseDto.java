package com.kiran.hospital.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDto {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private LocalDate birthDate;
}
