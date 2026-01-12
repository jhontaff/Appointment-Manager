package com.appointmentmanager.repository;

import com.appointmentmanager.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByClientId(Long clientId);
    List<Appointment> findByAdvisorId(Long advisorId);
    List<Appointment> findByAppointmentDate(LocalDate date);
    Boolean existsByAdvisorIdAndAppointmentDateAndAppointmentHour(Long advisorId, LocalDate appointmentDate, LocalTime appointmentHour);
}
