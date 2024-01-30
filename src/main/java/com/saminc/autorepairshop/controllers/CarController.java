package com.saminc.autorepairshop.controllers;

import com.saminc.autorepairshop.models.dtos.CarDTO;
import com.saminc.autorepairshop.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO){
        return ResponseEntity.ok(carService.createCar(carDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> replaceCar(@PathVariable Long id, @Valid @RequestBody CarDTO carDTO) {
        return ResponseEntity.ok(carService.replaceCar(id, carDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable Long id) {
        return ResponseEntity.ok(carService.deleteCar(id));
    }
}
