package com.saminc.autorepairshop.controllers;

import com.saminc.autorepairshop.models.dtos.MechanicDTO;
import com.saminc.autorepairshop.services.MechanicService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/mechanics")
public class MechanicController {
    MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @PostMapping
    public ResponseEntity<MechanicDTO> createMechanic(@Valid @RequestBody MechanicDTO mechanicDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mechanicService.createMechanic(mechanicDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MechanicDTO> getMechanic(@PathVariable Long id) {
        return ResponseEntity.ok(mechanicService.getMechanic(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MechanicDTO> replaceMechanic(@PathVariable Long id, @Valid @RequestBody MechanicDTO mechanicDTO) {
        return ResponseEntity.ok(mechanicService.replaceMechanic(id, mechanicDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MechanicDTO> deleteMechanic(@PathVariable Long id) {
        return ResponseEntity.ok(mechanicService.deleteMechanic(id));
    }
}
