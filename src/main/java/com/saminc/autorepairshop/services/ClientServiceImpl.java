package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.ClientDTO;
import com.saminc.autorepairshop.models.entities.Client;
import com.saminc.autorepairshop.repositories.CarRepository;
import com.saminc.autorepairshop.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService{

    private final ModelMapper modelMapper;
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ModelMapper modelMapper, CarRepository carRepository, ClientRepository clientRepository) {
        this.modelMapper = modelMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        log.info(String.valueOf(clientDTO));
        Client clientEntity = modelMapper.map(clientDTO, Client.class);
        log.info(String.valueOf(clientEntity));
        Client clientSavedEntity = clientRepository.save(clientEntity);
        log.info(String.valueOf(clientEntity));
        return modelMapper.map(clientSavedEntity, ClientDTO.class);
    }
}
