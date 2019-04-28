package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.model.Order;

import java.util.List;

public interface OrderService {

    Order getById(Long id);

    void save(Order order);

    void delete(Long id);

    List<Order> getAll();
}
