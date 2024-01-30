package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.models.entities.Client;
import com.saminc.autorepairshop.models.entities.Order;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CarDTO {
    private Long id;
    @NotBlank(message = "invalid license plate")
    private String licensePlate;
    @NotBlank(message = "invalid brand")
    private String brand;
    @NotBlank(message = "invalid model")
    private String model;
    @NotNull(message = "invalid age")
    @Max(value = 100, message = "we don't service cars older than 100 years")
    private Integer age;
    @NotNull(message = "invalid millage")
    private Double millage;
    @NotNull(message = "invalid checkup date")
    private LocalDate nextCheckupDate;

    @NotNull(message = "invalid client id")
    private Long clientId;
    private List<Long> orderList;
}
