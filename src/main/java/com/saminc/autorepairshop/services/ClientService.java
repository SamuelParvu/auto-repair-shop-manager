package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.ClientDTO;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
}
