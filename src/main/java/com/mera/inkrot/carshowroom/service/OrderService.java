package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.model.Order;

import java.util.List;
import java.util.Set;

public interface OrderService {

    Order getById(Long id);

    void save(OrderDto order);

    void addOption(Order order, Option option);

    void setOptions(Order order, Set<Option> options);

    void removeOption(Order order, Option option);

    List<Option> getOptions(Long orderId);

    void delete(Long id);

    List<Order> getAll();
}
