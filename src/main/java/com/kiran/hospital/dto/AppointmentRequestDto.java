package com.kiran.hospital.dto;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {
	
	@NotNull(message = "patient id is required")
    private Long patientId;
	
	@NotNull(message = "doctor id is required")
    private Long doctorId;
	
	@NotNull(message = "appointment time is required")
    private LocalDateTime appointmentTime;
	
	@NotBlank(message = "reason should be specified")
    private String reason;
}
