package com.appointmentmanager.dto.request;

import com.appointmentmanager.entity.AppointmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentCreateRequest {
    @NotNull(message = "Client is required")
    private Long clientId;

    @NotNull(message = "Advisor is required")
    private Long advisorId;

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment hour is required")
    private LocalTime appointmentHour;

    @NotNull(message = "Appointment type is required")
    private AppointmentType appointmentType;

    @Size(max = 300, message = "Notes cannot exceed 300 characters")
    private String notes;
}
