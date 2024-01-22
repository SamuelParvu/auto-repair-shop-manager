package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.ClientDTO;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO getClient(Long id);

    ClientDTO replaceClient(Long id, ClientDTO clientDTO);

    ClientDTO deleteClient(Long id);
}
