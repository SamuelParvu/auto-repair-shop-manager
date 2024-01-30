package com.saminc.autorepairshop.controllers;

import com.saminc.autorepairshop.models.dtos.ClientDTO;
import com.saminc.autorepairshop.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/clients")
public class ClientController {
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.createClient(clientDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> replaceClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.replaceClient(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDTO> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.deleteClient(id));
    }
}
