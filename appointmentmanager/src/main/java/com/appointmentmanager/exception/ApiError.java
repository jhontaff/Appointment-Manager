package com.appointmentmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private List<String> details;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }
}
