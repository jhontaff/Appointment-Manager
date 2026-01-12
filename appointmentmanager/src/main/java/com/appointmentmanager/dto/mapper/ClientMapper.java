package com.appointmentmanager.dto.mapper;

import com.appointmentmanager.dto.request.ClientCreateRequest;
import com.appointmentmanager.dto.request.ClientUpdateRequest;
import com.appointmentmanager.dto.response.ClientResponse;
import com.appointmentmanager.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "clientState", constant = "ACTIVATION_PENDING")
    Client toEntity(ClientCreateRequest dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "clientState", source = "clientState")
    ClientResponse toDto(Client client);

    @Mapping(target = "clientState", source = "clientState")
    void updateEntityFromDto(
            ClientUpdateRequest dto,
            @MappingTarget Client entity
    );



}
