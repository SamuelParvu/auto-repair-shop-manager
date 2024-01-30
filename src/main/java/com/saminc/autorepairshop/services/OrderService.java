package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.OrderDTO;
import com.saminc.autorepairshop.utils.OrderCategory;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    OrderDTO replaceOrder(Long id, OrderDTO orderDTO);

    OrderDTO deleteOrder(Long id);

    List<OrderDTO> filterOrders(LocalDate dateStart, LocalDate dateEnd, Double profitStart, Double profitEnd, OrderCategory orderCategory);
}
