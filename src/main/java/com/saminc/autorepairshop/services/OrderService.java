package com.saminc.autorepairshop.services;

import com.saminc.autorepairshop.models.dtos.OrderDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO getOrder(Long id);

    OrderDTO replaceOrder(Long id, OrderDTO orderDTO);

    OrderDTO deleteOrder(Long id);
}
