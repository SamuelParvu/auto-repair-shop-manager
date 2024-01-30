package com.saminc.autorepairshop.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saminc.autorepairshop.models.dtos.ClientDTO;
import com.saminc.autorepairshop.models.entities.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@Transactional
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMVC;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createClientTest_ShouldPass() throws Exception{
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("John");
        clientDTO.setLastName("Doe");
        clientDTO.setEmail("john@gmail.com");
        clientDTO.setPhoneNumber("+40123456789");

        mockMVC.perform(MockMvcRequestBuilders.post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO))).
        andExpect(status().isCreated());
    }

}
