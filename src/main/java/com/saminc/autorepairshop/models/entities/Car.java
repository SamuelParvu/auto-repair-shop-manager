package com.saminc.autorepairshop.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "age")
    private Integer age;
    @Column(name = "millage")
    private Double millage;
    @Column(name = "next_checkup_date")
    private LocalDate nextCheckupDate;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ToString.Exclude
    @OneToMany(mappedBy = "car")
    private List<Order> orderList = new ArrayList<>();
}
