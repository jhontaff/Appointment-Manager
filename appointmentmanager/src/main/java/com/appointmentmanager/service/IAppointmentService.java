package com.appointmentmanager.service;

import com.appointmentmanager.dto.request.AppointmentRequest;
import com.appointmentmanager.dto.response.AppointmentResponse;
import com.appointmentmanager.entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {

    public List<AppointmentResponse> getAppointments();
    public List<AppointmentResponse> getAppointmentByAdvisorId(Long id);
    public List<AppointmentResponse> getAppointmentByClientId(Long id);
    public List<AppointmentResponse> getAppointmentByDate(LocalDate date);
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest);
    public void deleteAppointment(Long id);
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest);

}
