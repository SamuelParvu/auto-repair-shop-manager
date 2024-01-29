package com.saminc.autorepairshop.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MechanicDTO {
    private Long id;

    private String firstName;
    private String lastName;
    private Double salary;
    private Integer yearsExperience;
    private Integer yearsTogether;

    private List<Long> orderList;
}
