package com.appointmentmanager.service;

import com.appointmentmanager.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {

    public List<Appointment> getAppointments();
    public List<Appointment> getAppointmentByAdvisorId(Long id);
    public List<Appointment> getAppointmentByClientId(Long id);
    public List<Appointment> getAppointmentByDate(LocalDate date);
    public Appointment createAppointment(Appointment appointment);
    public void deleteAppointment(Long id);
    public void updateAppointment(Long id, Appointment appointment);

}
