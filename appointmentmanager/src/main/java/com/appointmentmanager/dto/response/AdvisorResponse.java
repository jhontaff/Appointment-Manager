package com.appointmentmanager.dto.response;

import com.appointmentmanager.entity.AdvisorState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.List;

@Data
public class AdvisorResponse {

    private Long id;
    private String name;
    private String email;
    private String specialty;
    private String documentNumber;
    @Enumerated(EnumType.STRING)
    private AdvisorState advisorState;
    private List<AppointmentResponse> appointments;

}