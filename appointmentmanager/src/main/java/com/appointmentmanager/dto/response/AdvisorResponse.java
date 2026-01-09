package com.appointmentmanager.dto.response;

import com.appointmentmanager.entity.AdvisorState;
import lombok.Data;

import java.util.List;

@Data
public class AdvisorResponse {

    private Long id;
    private String name;
    private String email;
    private String specialty;
    private String documentNumber;
    private AdvisorState advisorState;
    private List<AppointmentResponse> appointments;

}