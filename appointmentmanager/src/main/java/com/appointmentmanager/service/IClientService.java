package com.appointmentmanager.service;

import com.appointmentmanager.dto.request.ClientCreateRequest;
import com.appointmentmanager.dto.request.ClientUpdateRequest;
import com.appointmentmanager.dto.response.ClientResponse;

import java.util.List;

public interface IClientService {

    public List<ClientResponse> getClients();
    public List<ClientResponse> searchClient(String document, String name);
    public ClientResponse getClientById(Long id);
    public ClientResponse createClient(ClientCreateRequest client);
    public void deleteClient(Long id);
    public ClientResponse updateClient(Long id, ClientUpdateRequest client);
}
