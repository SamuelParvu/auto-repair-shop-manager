package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.models.entities.Car;
import com.saminc.autorepairshop.models.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    private List<Car> carList;
    private List<Order> orderList;
}
