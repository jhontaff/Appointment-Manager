package com.appointmentmanager.dto.mapper;

import com.appointmentmanager.dto.request.ClientCreateRequest;
import com.appointmentmanager.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    Client toEntity(ClientCreateRequest dto);

}
