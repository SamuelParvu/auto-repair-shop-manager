package com.saminc.autorepairshop.repositories;

import com.saminc.autorepairshop.models.entities.Order;
import com.saminc.autorepairshop.utils.OrderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByDateProgrammedBetweenAndProfitBetweenAndCategory(LocalDate dateStart, LocalDate dateEnd, Double profitStart, Double profitEnd, OrderCategory orderCategory);
}
