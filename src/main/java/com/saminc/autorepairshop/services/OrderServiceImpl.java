package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.exceptions.IdNotFoundException;
import com.saminc.autorepairshop.models.dtos.OrderDTO;
import com.saminc.autorepairshop.models.entities.Mechanic;
import com.saminc.autorepairshop.models.entities.Order;
import com.saminc.autorepairshop.repositories.CarRepository;
import com.saminc.autorepairshop.repositories.ClientRepository;
import com.saminc.autorepairshop.repositories.MechanicRepository;
import com.saminc.autorepairshop.repositories.OrderRepository;
import com.saminc.autorepairshop.utils.OrderCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final MechanicRepository mechanicRepository;

    private final String ORDER_ID_NOT_FOUND_MESSAGE = "id does not correspond to any order entity";

    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository, CarRepository carRepository, MechanicRepository mechanicRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
        this.mechanicRepository = mechanicRepository;


    }


    public Order DTOtoOrder(OrderDTO orderDTO) {
        Order orderEntity = new Order();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setDateProgrammed(orderDTO.getDateProgrammed());
        orderEntity.setCategory(orderDTO.getCategory());
        orderEntity.setNotes(orderDTO.getNotes());
        orderEntity.setIncome(orderDTO.getIncome());
        orderEntity.setCost(orderDTO.getCost());
        orderEntity.setProfit(orderDTO.getIncome() - orderDTO.getCost());

        orderEntity.setCar(carRepository.findById(orderDTO.getCarId())
                .orElseThrow(() -> new IdNotFoundException("car id doesn't exist")));
        orderEntity.setClient(orderEntity.getCar().getClient());
        List<Mechanic> mechanicList = orderDTO.getMechanicIdList().stream()
                .map(mechanicId -> mechanicRepository.findById(mechanicId)
                        .orElseThrow(() -> new IdNotFoundException("one or more mechanic ids don't exist, failed at id: " + mechanicId)))
                .toList();
        orderEntity.setMechanicList(mechanicList);
        return orderEntity;
    }

    public OrderDTO orderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setDateProgrammed(order.getDateProgrammed());
        orderDTO.setCategory(order.getCategory());
        orderDTO.setNotes(order.getNotes());
        orderDTO.setIncome(order.getIncome());
        orderDTO.setCost(order.getCost());
        orderDTO.setProfit(order.getProfit());

        orderDTO.setClientId(order.getClient().getId());
        orderDTO.setCarId(order.getCar().getId());
        orderDTO.setMechanicIdList(order.getMechanicList().stream()
                .map(Mechanic::getId)
                .toList());
        return orderDTO;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderDTO.setId(null); // prevent updating entities in create method
        Order orderEntity = DTOtoOrder(orderDTO);
        Order orderSavedEntity = orderRepository.save(orderEntity);
        log.info("new order has been created");
        return orderToDTO(orderSavedEntity);
    }

    @Override
    public OrderDTO getOrder(Long id) {
        log.info("finding order with id:{}", id);
        return orderToDTO(orderRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(ORDER_ID_NOT_FOUND_MESSAGE)));
    }

    @Override
    public OrderDTO replaceOrder(Long id, OrderDTO orderDTO) {
        if (!orderRepository.existsById(id)) {
            throw new IdNotFoundException(ORDER_ID_NOT_FOUND_MESSAGE);
        }
        orderDTO.setId(id);
        Order orderEntity = DTOtoOrder(orderDTO);
        Order orderSavedEntity = orderRepository.save(orderEntity);
        log.info("order with id:{} has been updated with PUT", id);
        return orderToDTO(orderSavedEntity);
    }

    @Override
    public OrderDTO deleteOrder(Long id) {
        OrderDTO deletedOrder = getOrder(id);
        orderRepository.deleteById(id);
        log.info("deleted order with id:{}", id);
        return deletedOrder;
    }

    @Override
    public List<OrderDTO> filterOrders(LocalDate dateStart, LocalDate dateEnd, Double profitStart, Double profitEnd, OrderCategory orderCategory) {
        List<Order> resultEntityList = orderRepository.findAllByDateProgrammedBetweenAndProfitBetweenAndCategory(dateStart,dateEnd,profitStart,profitEnd,orderCategory);
        List<OrderDTO> resultDTOList = resultEntityList.stream()
                .map(this::orderToDTO)
                .toList();
        return resultDTOList;
    }
}
