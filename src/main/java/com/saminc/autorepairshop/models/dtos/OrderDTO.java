package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.utils.OrderCategory;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private Long id;

    private LocalDate dateProgrammed;
    private OrderCategory category;
    private String notes;
    private Double income;
    private Double cost;
    private Double profit;


    private Long clientId;
    private Long carId;
    private List<Long> mechanicList;
}