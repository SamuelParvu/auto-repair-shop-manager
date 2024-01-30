package com.saminc.autorepairshop.unit_tests;

import com.saminc.autorepairshop.exceptions.IdNotFoundException;
import com.saminc.autorepairshop.models.dtos.ClientDTO;
import com.saminc.autorepairshop.models.entities.Client;
import com.saminc.autorepairshop.repositories.ClientRepository;
import com.saminc.autorepairshop.services.ClientServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.oauth2.server.servlet.OAuth2AuthorizationServerProperties;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void testConvertDTOtoEntity_ShouldPass(){
        //GIVEN
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setFirstName("John");
        clientDTO.setLastName("Doe");
        clientDTO.setEmail("john@gmail.com");
        clientDTO.setPhoneNumber("+40123456789");

        Client expectedClientEntity = new Client();
        expectedClientEntity.setId(1L);
        expectedClientEntity.setFirstName("John");
        expectedClientEntity.setLastName("Doe");
        expectedClientEntity.setEmail("john@gmail.com");
        expectedClientEntity.setPhoneNumber("+40123456789");

        //WHEN
        Client actualClientEntity = clientService.DTOtoClient(clientDTO);

        //THEN
        assertEquals(expectedClientEntity,actualClientEntity);
    }

    @Test
    void testConvertEntityToDTO_ShouldPass(){
        //GIVEN
        Client clientEntity = new Client();
        clientEntity.setId(1L);
        clientEntity.setFirstName("John");
        clientEntity.setLastName("Doe");
        clientEntity.setEmail("john@gmail.com");
        clientEntity.setPhoneNumber("+40123456789");
        clientEntity.setCarList(new ArrayList<>());
        clientEntity.setOrderList(new ArrayList<>());

        ClientDTO expectedDTO = new ClientDTO();
        expectedDTO.setId(1L);
        expectedDTO.setFirstName("John");
        expectedDTO.setLastName("Doe");
        expectedDTO.setEmail("john@gmail.com");
        expectedDTO.setPhoneNumber("+40123456789");
        expectedDTO.setCarIdList(new ArrayList<>());
        expectedDTO.setOrderIdList(new ArrayList<>());
        //WHEN
        ClientDTO actualDTO = clientService.clientToDTO(clientEntity);

        //THEN
        assertEquals(expectedDTO,actualDTO);
    }
}
