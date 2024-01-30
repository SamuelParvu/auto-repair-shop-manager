package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.models.entities.Car;
import com.saminc.autorepairshop.models.entities.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {

    private Long id;

    @NotBlank(message = "invalid first name")
    private String firstName;
    @NotBlank(message = "invalid last name")
    private String lastName;
    @NotBlank(message = "invalid email")
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "invalid phone number")
    private String phoneNumber;

    private List<Long> carIdList;
    private List<Long> orderIdList;
}
