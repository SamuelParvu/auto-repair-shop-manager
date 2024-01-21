package com.saminc.autorepairshop.models.entities;

import com.saminc.autorepairshop.utils.OrderCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_programmed")
    private LocalDate dateProgrammed;
    @Column(name = "category")
    private OrderCategory category;
    @Column(name = "notes")
    private String notes;
    @Column(name = "income")
    private Double income;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "profit")
    private Double profit;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_mechanics",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "mechanic_id")
    )
    private List<Mechanic> mechanicList;
}
