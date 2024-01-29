package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.exceptions.IdNotFoundException;
import com.saminc.autorepairshop.models.dtos.CarDTO;
import com.saminc.autorepairshop.models.entities.Car;
import com.saminc.autorepairshop.models.entities.Order;
import com.saminc.autorepairshop.repositories.CarRepository;
import com.saminc.autorepairshop.repositories.ClientRepository;
import com.saminc.autorepairshop.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    public CarServiceImpl(ModelMapper modelMapper, CarRepository carRepository, ClientRepository clientRepository, OrderRepository orderRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    public Car DTOtoCar(CarDTO carDTO) {
        Car carEntity = modelMapper.map(carDTO, Car.class);
        carEntity.setClient(clientRepository.findById(carDTO.getClientId())
                .orElseThrow(() -> new IdNotFoundException("client id does not exist")));
        List<Order> orderList = carDTO.getOrderList().stream()
                .map(orderId -> orderRepository.findById(orderId)
                        .orElseThrow(() -> new IdNotFoundException("order id does not exist " + orderId)))
                .toList();
//        List<Order> orderList2 = new ArrayList<>();
//        for (Long orderId : carDTO.getOrderList()) {
//            orderList2.add(orderRepository.findById(orderId)
//                    .orElseThrow(() -> new IdNotFoundException("no such id")));
//        }
        carEntity.setOrderList(orderList);
        return carEntity;
    }

    public CarDTO carToDTO(Car carEntity) {
        CarDTO carDTO = modelMapper.map(carEntity, CarDTO.class);
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
        log.info("new car has been created");
        return carToDTO(carSavedEntity);
    }

    @Override
    public CarDTO getCar(Long id) {
        checkIfIdExists(id);
        log.info("Found Car with id:{}", id);
        return carToDTO(carRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("id not found")));
    }

    @Override
    public CarDTO replaceCar(Long id, CarDTO carDTO) {
        checkIfIdExists(id);
        Car carEntity = DTOtoCar(carDTO);
        carEntity.setId(id);
        Car carSavedEntity = carRepository.save(carEntity);
        log.info("car with id:{} has been updated with PUT", id);
        return carToDTO(carSavedEntity);
    }

    @Override
    public CarDTO deleteCar(Long id) {
        checkIfIdExists(id);
        log.info("Deleting Car with id:{}", id);
        CarDTO deletedCar = getCar(id);
        carRepository.deleteById(id);
        return deletedCar;
    }

    public void checkIfIdExists(Long id) {
        if (!carRepository.existsById(id)) {
            throw new IdNotFoundException("Id does not correspond to any car entity");
        }
    }
}
