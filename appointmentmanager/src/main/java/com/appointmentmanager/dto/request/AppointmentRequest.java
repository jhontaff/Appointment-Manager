package com.appointmentmanager.dto.request;

import com.appointmentmanager.entity.AppointmentState;
import com.appointmentmanager.entity.AppointmentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {
    @NotNull(message = "Client is required")
    private Long clientId;

    @NotNull(message = "Advisor is required")
    private Long advisorId;

    @NotNull(message = "Appointment date is required")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment hour is required")
    private LocalTime appointmentHour;

    @Enumerated(EnumType.STRING)
    private AppointmentState appointmentState;

    @Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;

    @Size(max = 300, message = "Notes cannot exceed 300 characters")
    private String notes;
}
