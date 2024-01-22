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
        Client clientEntity = modelMapper.map(clientDTO, Client.class);
        Client clientSavedEntity = clientRepository.save(clientEntity);
        log.info("new client has been created");
        return modelMapper.map(clientSavedEntity, ClientDTO.class);
    }

    @Override
    public ClientDTO getClient(Long id) {
        log.info("Finding Client with id:{}",id);
        return modelMapper.map(clientRepository.findById(id).orElseThrow(), ClientDTO.class);
    }

    @Override
    public ClientDTO replaceClient(Long id, ClientDTO clientDTO) {
        Client clientEntity = modelMapper.map(clientDTO, Client.class);
        clientEntity.setId(id);
        if (!clientRepository.existsById(id)){
            throw new IllegalArgumentException("id does not correspond to any entry");
        }
        Client clientSavedEntity = clientRepository.save(clientEntity);
        log.info("a client has been updated with PUT");
        return modelMapper.map(clientSavedEntity, ClientDTO.class);
    }

    @Override
    public ClientDTO deleteClient(Long id) {
        log.info("Deleting Client with id:{}",id);
        ClientDTO deletedClient = getClient(id);
        clientRepository.deleteById(id);
        return deletedClient;
    }
}