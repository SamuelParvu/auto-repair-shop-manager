package com.saminc.autorepairshop.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "mechanics")
public class Mechanic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "years_experience")
    private Integer yearsExperience;
    @Column(name = "years_together")
    private Integer yearsTogether;

    @ManyToMany(mappedBy = "mechanicList")
    private List<Order> orderList;
}
