package com.saminc.autorepairshop.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;

    @OneToMany(mappedBy = "client")
    private List<Car> carList;
    @OneToMany(mappedBy = "client")
    private List<Order> orderList;
}
