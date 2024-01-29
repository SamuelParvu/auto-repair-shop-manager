package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.models.entities.Client;
import com.saminc.autorepairshop.models.entities.Order;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CarDTO {
    private Long id;

    private String licensePlate;
    private String brand;
    private String model;
    private Integer age;
    private Double millage;
    private LocalDate nextCheckupDate;

    private Long clientId;
    private List<Long> orderList;
}
