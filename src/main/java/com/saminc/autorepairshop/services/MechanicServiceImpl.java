package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.exceptions.IdNotFoundException;
import com.saminc.autorepairshop.models.dtos.MechanicDTO;
import com.saminc.autorepairshop.models.entities.Mechanic;
import com.saminc.autorepairshop.models.entities.Order;
import com.saminc.autorepairshop.repositories.MechanicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final String MECHANIC_ID_NOT_FOUND_MESSAGE = "id does not correspond to any mechanic entity";


    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public Mechanic DTOtoMechanic(MechanicDTO mechanicDTO) {
        Mechanic mechanicEntity = new Mechanic();
        mechanicEntity.setId(mechanicDTO.getId());
        mechanicEntity.setFirstName(mechanicDTO.getFirstName());
        mechanicEntity.setLastName(mechanicDTO.getLastName());
        mechanicEntity.setSalary(mechanicDTO.getSalary());
        mechanicEntity.setYearsExperience(mechanicDTO.getYearsExperience());
        mechanicEntity.setYearsTogether(mechanicDTO.getYearsTogether());
        return mechanicEntity;
    }

    public MechanicDTO mechanicToDTO(Mechanic mechanic) {
        MechanicDTO mechanicDTO = new MechanicDTO();
        mechanicDTO.setId(mechanic.getId());
        mechanicDTO.setFirstName(mechanic.getFirstName());
        mechanicDTO.setLastName(mechanic.getLastName());
        mechanicDTO.setSalary(mechanic.getSalary());
        mechanicDTO.setYearsExperience(mechanic.getYearsExperience());
        mechanicDTO.setYearsTogether(mechanic.getYearsTogether());

        mechanicDTO.setOrderList(mechanic.getOrderList().stream()
                .map(Order::getId)
                .toList());
        return mechanicDTO;
    }

    @Override
    public MechanicDTO createMechanic(MechanicDTO mechanicDTO) {
        mechanicDTO.setId(null); // prevent updating entities in create method
        Mechanic mechanicEntity = DTOtoMechanic(mechanicDTO);
        Mechanic mechanicSavedEntity = mechanicRepository.save(mechanicEntity);
        log.info("new mechanic has been created");
        return mechanicToDTO(mechanicSavedEntity);
    }

    @Override
    public MechanicDTO getMechanic(Long id) {
        log.info("finding mechanic with id:{}", id);
        return mechanicToDTO(mechanicRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(MECHANIC_ID_NOT_FOUND_MESSAGE)));
    }

    @Override
    public MechanicDTO replaceMechanic(Long id, MechanicDTO mechanicDTO) {
        if (!mechanicRepository.existsById(id)) {
            throw new IdNotFoundException(MECHANIC_ID_NOT_FOUND_MESSAGE);
        }
        mechanicDTO.setId(id);
        Mechanic mechanicEntity = DTOtoMechanic(mechanicDTO);
        Mechanic mechanicSavedEntity = mechanicRepository.save(mechanicEntity);
        log.info("mechanic with id:{} has been updated with PUT", id);
        return mechanicToDTO(mechanicSavedEntity);
    }

    @Override
    public MechanicDTO deleteMechanic(Long id) {
        MechanicDTO deletedMechanic = getMechanic(id);
        mechanicRepository.deleteById(id);
        log.info("deleted mechanic with id:{}", id);
        return deletedMechanic;
    }
}
