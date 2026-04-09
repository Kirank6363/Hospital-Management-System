package com.kiran.hospital.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kiran.hospital.dto.AppointmentRequestDto;
import com.kiran.hospital.dto.AppointmentResponseDto;
import com.kiran.hospital.service.AppointmentService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;


    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(@Valid @RequestBody AppointmentRequestDto appointment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createAppointment(appointment));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> updateAppointmentById(@PathVariable Long id, @Valid @RequestBody AppointmentRequestDto dto){
    	return ResponseEntity.ok(appointmentService.updateAppointmentById(id, dto));
    }
    
    
    
    
    
}









