package com.appointmentmanager.controller;

import com.appointmentmanager.dto.request.AppointmentRequest;
import com.appointmentmanager.dto.response.AppointmentResponse;
import com.appointmentmanager.service.IAppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAll(){
        List<AppointmentResponse> appointments = this.appointmentService.getAppointments();
        return appointments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(appointments);
    }

    @GetMapping("/date")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDate(@RequestParam LocalDate date){
        List<AppointmentResponse> appointments = this.appointmentService.getAppointmentByDate((date));
        return appointments.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(appointments);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(this.appointmentService.getAppointmentByAdvisorId(id).get(0));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest){
        AppointmentResponse createdAppointment = this.appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.status(201).body(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentRequest appointmentRequest){
        return ResponseEntity.ok(this.appointmentService.updateAppointment(id, appointmentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        this.appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }



}
