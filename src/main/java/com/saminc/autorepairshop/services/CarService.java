package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.CarDTO;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);

    CarDTO getCar(Long id);

    CarDTO replaceCar(Long id, CarDTO carDTO);

    CarDTO deleteCar(Long id);
}
