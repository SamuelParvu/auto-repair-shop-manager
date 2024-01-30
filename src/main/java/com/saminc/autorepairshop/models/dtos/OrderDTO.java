package com.saminc.autorepairshop.models.dtos;

import com.saminc.autorepairshop.utils.OrderCategory;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    @NotNull(message = "invalid date programmed")
    private LocalDate dateProgrammed;
    @NotNull(message = "invalid category")
    private OrderCategory category;
    @NotBlank(message = "invalid notes")
    @Size(max = 1024, message = "notes cannot be more than 1024 characters")
    private String notes;
    @NotNull(message = "invalid income")
    private Double income;
    @NotNull(message = "invalid cost")
    private Double cost;
    private Double profit;

    private Long clientId;
    private Long carId;
    private List<Long> mechanicIdList;
}
