package com.appointmentmanager.dto.mapper;

import com.appointmentmanager.dto.request.CreateAdvisorRequest;
import com.appointmentmanager.entity.Advisor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdvisorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Advisor toEntity(CreateAdvisorRequest dto);
}
