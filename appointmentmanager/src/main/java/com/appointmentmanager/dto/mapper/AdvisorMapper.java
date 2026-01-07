package com.appointmentmanager.dto.mapper;

import com.appointmentmanager.dto.request.AdvisorCreateRequest;
import com.appointmentmanager.dto.request.AdvisorUpdateRequest;
import com.appointmentmanager.dto.response.AdvisorResponse;
import com.appointmentmanager.entity.Advisor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdvisorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Advisor toEntity(AdvisorCreateRequest dto);

    void updateEntityFromDto(
            AdvisorUpdateRequest dto,
            @MappingTarget Advisor entity
    );

    AdvisorResponse toDto(Advisor entity);
}
