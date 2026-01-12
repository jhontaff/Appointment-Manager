package com.appointmentmanager.dto.mapper;

import com.appointmentmanager.dto.request.AppointmentRequest;
import com.appointmentmanager.dto.response.AppointmentResponse;
import com.appointmentmanager.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointmentState", constant = "SCHEDULED")
    @Mapping(target = "advisor.id", source = "advisorId")
    @Mapping(target = "client.id", source = "clientId")
    Appointment toEntity(AppointmentRequest dto);


    void updateEntityFromDto(
            AppointmentRequest dto,
            @MappingTarget Appointment entity
    );

    @Mapping(target = "advisorId", source = "advisor.id")
    @Mapping(target = "clientId", source = "client.id")
    AppointmentResponse toDto(Appointment appointment);

}
