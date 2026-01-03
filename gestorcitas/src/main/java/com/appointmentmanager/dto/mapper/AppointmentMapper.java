package com.appointmentmanager.dto.mapper;

import com.appointmentmanager.dto.request.CreateAppointmentRequest;
import com.appointmentmanager.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "advisor", ignore = true)
    @Mapping(target = "appointmentState", ignore = true)
    Appointment toEntity(CreateAppointmentRequest dto);
}
