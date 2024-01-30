package com.saminc.autorepairshop.controllers;

import com.saminc.autorepairshop.models.dtos.OrderDTO;
import com.saminc.autorepairshop.services.OrderService;
import com.saminc.autorepairshop.utils.OrderCategory;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/orders")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> replaceOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.replaceOrder(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> filterOrders(@RequestParam LocalDate dateStart, @RequestParam LocalDate dateEnd, @RequestParam Double profitStart, @RequestParam Double profitEnd, @RequestParam OrderCategory orderCategory){
        return ResponseEntity.ok(orderService.filterOrders(dateStart, dateEnd, profitStart, profitEnd, orderCategory));
    }
}
