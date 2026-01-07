package com.appointmentmanager.dto.request;

import com.appointmentmanager.entity.ClientState;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClientCreateRequest {

    @NotBlank
    @Size(min=4, max=20, message="client name must be between 4 and 20 characters")
    private String name;

    @NotBlank(message = "email can't be empty")
    @Email(message = "email should be valid")
    private String email;

    @NotBlank(message = "document number can't be empty")
    @Size(min=5, max=15, message="document number must be between 5 and 15 characters")
    private String documentNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private ClientState clientState;

}
