package com.appointmentmanager.service.impl;

import com.appointmentmanager.dto.mapper.ClientMapper;
import com.appointmentmanager.dto.request.ClientCreateRequest;
import com.appointmentmanager.dto.request.ClientUpdateRequest;
import com.appointmentmanager.dto.response.ClientResponse;
import com.appointmentmanager.entity.Client;
import com.appointmentmanager.exception.BusinessException;
import com.appointmentmanager.repository.ClientRepository;
import com.appointmentmanager.service.IClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private  final ClientMapper clientMapper;
    public ClientServiceImpl(ClientRepository clientRepository,
                             ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }


    @Override
    public List<ClientResponse> getClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public List<ClientResponse> searchClient(String document, String name) {
        return List.of();
    }

    @Override
    public ClientResponse getClientById(Long id) {
        Client entity = clientRepository.findById(id)
                .orElseThrow(
                () -> new BusinessException("Client with id " + id + " not found")
        );
        return clientMapper.toDto(entity);
    }

    @Override
    public ClientResponse createClient(ClientCreateRequest client) {
        return null;
    }

    @Override
    public void deleteClient(Long id) {
        if(!clientRepository.existsById(id)) {
            throw new BusinessException("Client with id " + id + " not found");
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientResponse updateClient(Long id, ClientUpdateRequest clientUpdateRequest) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(
                        () -> new BusinessException("Client with id " + id + " not found")
                );

        clientMapper.updateEntityFromDto(clientUpdateRequest, existingClient);
        Client updatedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(updatedClient );
    }
}
