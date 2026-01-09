package com.appointmentmanager.dto.response;

import com.appointmentmanager.entity.ClientState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class ClientResponse {

    private String name;
    private String email;
    private String documentNumber;
    private String birthDate;
    @Enumerated(EnumType.STRING)
    private ClientState clientState;
}
