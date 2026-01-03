package com.appointmentmanager.dto.response;

import com.appointmentmanager.entity.AppointmentState;
import com.appointmentmanager.entity.AppointmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentResponse {

    private Long id;

    private Long clientId;

    private Long advisorId;

    private LocalDate appointmentDate;

    private LocalTime appointmentHour;

    private AppointmentType appointmentType;

    private AppointmentState appointmentState;

    private String notes;
}
