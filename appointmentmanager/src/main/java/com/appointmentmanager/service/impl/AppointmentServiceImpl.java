package com.appointmentmanager.service.impl;

import com.appointmentmanager.dto.mapper.AppointmentMapper;
import com.appointmentmanager.dto.request.AppointmentRequest;
import com.appointmentmanager.dto.response.AppointmentResponse;
import com.appointmentmanager.entity.Appointment;
import com.appointmentmanager.exception.BusinessException;
import com.appointmentmanager.repository.AppointmentRepository;
import com.appointmentmanager.service.IAppointmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }


    @Override
    public List<AppointmentResponse> getAppointments() {
        return appointmentRepository.findAll().stream().map(appointmentMapper::toDto).toList();
    }

    @Override
    public List<AppointmentResponse> getAppointmentByAdvisorId(Long id) {
        List <Appointment> appointment = appointmentRepository.findByAdvisorId(id);
        return appointment.stream().map(appointmentMapper::toDto).toList();
    }

    @Override
    public List<AppointmentResponse> getAppointmentByClientId(Long id) {
        List <Appointment> appointment = appointmentRepository.findByClientId(id);
        return appointment.stream().map(appointmentMapper::toDto).toList();
    }


    @Override
    public List<AppointmentResponse> getAppointmentByDate(LocalDate date) {
        List<Appointment> appointment = appointmentRepository.findByAppointmentDate(date);
        return appointment.stream().map(appointmentMapper::toDto).toList();
    }

    @Override
    public AppointmentResponse createAppointment(AppointmentRequest appointmentRequest) {
        this.appointmentValidations(appointmentRequest);
        Appointment appointment = appointmentMapper.toEntity(appointmentRequest);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }

    private void validateAdvisorSchedulingConflict(AppointmentRequest appointmentRequest) {
        boolean conflictExists = appointmentRepository.existsByAdvisorIdAndAppointmentDateAndAppointmentHour(
                appointmentRequest.getAdvisorId(),
                appointmentRequest.getAppointmentDate(),
                appointmentRequest.getAppointmentHour()
        );

        if (conflictExists) {
            throw new BusinessException("Scheduling conflict: Advisor is already booked for the selected date and time.");
        }
    }

    private void validateClientSchedulingConflict(AppointmentRequest appointmentRequest) {
        boolean conflictExists = appointmentRepository.existsByAdvisorIdAndAppointmentDateAndAppointmentHour(
                appointmentRequest.getClientId(),
                appointmentRequest.getAppointmentDate(),
                appointmentRequest.getAppointmentHour()
        );

        if (conflictExists) {
            throw new BusinessException("Scheduling conflict: Client is already booked for the selected date and time.");
        }
    }

    private void validateAvailableScheduling(LocalDate date) {
        switch (date.getDayOfWeek()) {
            case SATURDAY, SUNDAY -> throw new BusinessException("Appointments cannot be scheduled on weekends.");
            default -> {
                // Weekdays are valid; no action needed
            }
        }
    }

    private void appointmentValidations(AppointmentRequest appointmentRequest) {
        validateAvailableScheduling(appointmentRequest.getAppointmentDate());
        validateAdvisorSchedulingConflict(appointmentRequest);
        validateClientSchedulingConflict(appointmentRequest);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Appointment with id " + id + " not found"));
        appointmentRepository.delete(appointment);
    }

    @Override
    public AppointmentResponse updateAppointment(Long id, AppointmentRequest appointmentRequest) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Appointment with id " + id + " not found"));

        appointmentMapper.updateEntityFromDto(appointmentRequest, appointment);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(savedAppointment);
    }
}
