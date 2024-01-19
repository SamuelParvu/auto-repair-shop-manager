package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.models.entities.Car;
import com.saminc.autorepairshop.models.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class ClientDTO {

    private Long id;

    private String firstName;
    private String last_name;
    private String email;
    private String phone_number;

    private List<Car> carList;
    private List<Order> orderList;
}
