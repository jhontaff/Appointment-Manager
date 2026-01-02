package com.appointmentmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "advisor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advisor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String specialty;
    private String email;

    @Enumerated(EnumType.STRING)
    private AdvisorState advisorState;

    @OneToMany(mappedBy = "advisor")
    private List<Appointment> appointments;

}
