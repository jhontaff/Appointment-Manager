package com.appointmentmanager.service;

import com.appointmentmanager.entity.Client;

import java.util.List;

public interface IClientService {

    public List<Client> getClients();
    public List<Client> searchClient(String document, String name);
    public Client getClientById(Long id);
    public Client createClient(Client client);
    public void deleteClient(Long id);
    public void updateClient(Long id, Client client);
}
