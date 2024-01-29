package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.exceptions.IdNotFoundException;
import com.saminc.autorepairshop.models.dtos.CarDTO;
import com.saminc.autorepairshop.models.entities.Car;
import com.saminc.autorepairshop.models.entities.Order;
import com.saminc.autorepairshop.repositories.CarRepository;
import com.saminc.autorepairshop.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private static final String CAR_ID_NOT_FOUND_MESSAGE = "Id does not correspond to any car entity";

    public CarServiceImpl(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public Car DTOtoCar(CarDTO carDTO) {
        Car carEntity = new Car();
        carEntity.setId(carDTO.getId());
        carEntity.setLicensePlate(carDTO.getLicensePlate());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setModel(carDTO.getModel());
        carEntity.setAge(carDTO.getAge());
        carEntity.setMillage(carDTO.getMillage());
        carEntity.setNextCheckupDate(carDTO.getNextCheckupDate());

        carEntity.setClient(clientRepository.findById(carDTO.getClientId())
                .orElseThrow(() -> new IdNotFoundException("client id not found!")));
        return carEntity;
    }

    public CarDTO carToDTO(Car carEntity) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carEntity.getId());
        carDTO.setLicensePlate(carEntity.getLicensePlate());
        carDTO.setBrand(carEntity.getBrand());
        carDTO.setModel(carEntity.getModel());
        carDTO.setAge(carEntity.getAge());
        carDTO.setMillage(carEntity.getMillage());
        carDTO.setNextCheckupDate(carEntity.getNextCheckupDate());

        carDTO.setClientId(carEntity.getClient().getId());
        List<Long> orderIdList = carEntity.getOrderList().stream()
                .map(Order::getId)
                .toList();
        carDTO.setOrderList(orderIdList);
        return carDTO;
    }


    @Override
    public CarDTO createCar(CarDTO carDTO) {
        carDTO.setId(null);
        Car carEntity = DTOtoCar(carDTO);
        Car carSavedEntity = carRepository.save(carEntity);
        log.info("new car has been created id:{}",carSavedEntity.getId());
        return carToDTO(carSavedEntity);
    }

    @Override
    public CarDTO getCar(Long id) {
        log.info("finding car with id:{}", id);
        return carToDTO(carRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(CAR_ID_NOT_FOUND_MESSAGE)));
    }

    @Override
    public CarDTO replaceCar(Long id, CarDTO carDTO) {
        if (!carRepository.existsById(id)) {
            throw new IdNotFoundException(CAR_ID_NOT_FOUND_MESSAGE);
        }
        Car carEntity = DTOtoCar(carDTO);
        carEntity.setId(id);
        Car carSavedEntity = carRepository.save(carEntity);
        log.info("car with id:{} has been updated with PUT", id);
        return carToDTO(carSavedEntity);
    }

    @Override
    public CarDTO deleteCar(Long id) {
        CarDTO deletedCar = getCar(id);
        carRepository.deleteById(id);
        log.info("deleted car with id:{}", id);
        return deletedCar;
    }
}
