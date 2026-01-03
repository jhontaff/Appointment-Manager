package com.appointmentmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "advisor_id", nullable = false)
    private Advisor advisor;

    private LocalDate appointmentDate;
    private LocalTime appointmentHour;

    @Enumerated(EnumType.STRING)
    private AppointmentState appointmentState;

    @Enumerated(EnumType.STRING)
    private AppointmentType appointmentType;

    private String notes;


}
