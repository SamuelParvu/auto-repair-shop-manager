package com.saminc.autorepairshop.models.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MechanicDTO {
    private Long id;

    @NotBlank(message = "invalid first name")
    private String firstName;
    @NotBlank(message = "invalid last name")
    private String lastName;
    @NotNull(message = "invalid salary")
    private Double salary;

    @NotNull(message = "invalid years of experience")
    @Max(value = 100, message = "the mechanic cannot have more than 100 years of experience")
    private Integer yearsExperience;
    @NotNull(message = "invalid years spent together")
    @Max(value = 100, message = "the mechanic cannot have more than 100 years together with us")
    private Integer yearsTogether;

    private List<Long> orderList;
}
