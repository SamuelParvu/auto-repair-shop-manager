package com.saminc.autorepairshop.controllers;

import com.saminc.autorepairshop.models.dtos.ClientDTO;
import com.saminc.autorepairshop.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clients")
public class ClientController {
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO){
        return  ResponseEntity.ok(clientService.createClient(clientDTO));
    }
}
