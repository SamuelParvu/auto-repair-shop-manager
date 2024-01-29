package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.MechanicDTO;

public interface MechanicService {
    MechanicDTO createMechanic(MechanicDTO mechanicDTO);

    MechanicDTO getMechanic(Long id);

    MechanicDTO replaceMechanic(Long id, MechanicDTO mechanicDTO);

    MechanicDTO deleteMechanic(Long id);
}
