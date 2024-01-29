package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.exceptions.IdNotFoundException;
import com.saminc.autorepairshop.models.dtos.ClientDTO;
import com.saminc.autorepairshop.models.entities.Car;
import com.saminc.autorepairshop.models.entities.Client;
import com.saminc.autorepairshop.models.entities.Order;
import com.saminc.autorepairshop.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final String CLIENT_ID_NOT_FOUND_MESSAGE = "id does not correspond to any client entity";


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client DTOtoClient(ClientDTO clientDTO) {
        Client clientEntity = new Client();
        clientEntity.setId(clientDTO.getId());
        clientEntity.setFirstName(clientDTO.getFirstName());
        clientEntity.setLastName(clientDTO.getLastName());
        clientEntity.setEmail(clientDTO.getEmail());
        clientEntity.setPhoneNumber(clientDTO.getPhoneNumber());
        return clientEntity;
    }

    public ClientDTO clientToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setCarIdList(client.getCarList().stream()
                .map(Car::getId)
                .toList());
        clientDTO.setOrderIdList(client.getOrderList().stream()
                .map(Order::getId)
                .toList());
        return clientDTO;
    }

    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        clientDTO.setId(null); // prevent updating entities in create method
        Client clientEntity = DTOtoClient(clientDTO);
        Client clientSavedEntity = clientRepository.save(clientEntity);
        log.info("new client has been created");
        return clientToDTO(clientSavedEntity);
    }

    @Override
    public ClientDTO getClient(Long id) {
        log.info("finding client with id:{}", id);
        return clientToDTO(clientRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE)));
    }

    @Override
    public ClientDTO replaceClient(Long id, ClientDTO clientDTO) {
        if (!clientRepository.existsById(id)) {
            throw new IdNotFoundException(CLIENT_ID_NOT_FOUND_MESSAGE);
        }
        clientDTO.setId(id);
        Client clientEntity = DTOtoClient(clientDTO);
        Client clientSavedEntity = clientRepository.save(clientEntity);
        log.info("client with id:{} has been updated with PUT", id);
        return clientToDTO(clientSavedEntity);
    }

    @Override
    public ClientDTO deleteClient(Long id) {
        ClientDTO deletedClient = getClient(id);
        clientRepository.deleteById(id);
        log.info("deleted client with id:{}", id);
        return deletedClient;
    }
}
