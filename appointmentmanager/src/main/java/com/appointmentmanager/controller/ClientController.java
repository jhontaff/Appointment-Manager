package com.appointmentmanager.controller;

import com.appointmentmanager.dto.request.ClientCreateRequest;
import com.appointmentmanager.dto.request.ClientUpdateRequest;
import com.appointmentmanager.dto.response.ClientResponse;
import com.appointmentmanager.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientResponse> clients = this.clientService.getClients();
        return clients.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.clientService.getClientById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientCreateRequest clientCreateRequest) {
        ClientResponse createdClient = this.clientService.createClient(clientCreateRequest);
        return ResponseEntity.status(201).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id, @Valid @RequestBody ClientUpdateRequest createUpdateRequest) {
        return ResponseEntity.ok(this.clientService.updateClient(id, createUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        this.clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
