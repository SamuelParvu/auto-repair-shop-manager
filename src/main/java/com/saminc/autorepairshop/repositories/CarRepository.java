package com.saminc.autorepairshop.repositories;

import com.saminc.autorepairshop.models.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

}
