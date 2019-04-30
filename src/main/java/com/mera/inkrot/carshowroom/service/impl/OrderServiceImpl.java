package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.repository.OrderRepository;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order getById(Long id) {
        logger.info("get order by id: {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if(! order.isPresent())
            throw new IllegalArgumentException("Order not found");
        return order.get();
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
        logger.info("save order: {}", order.getId());
    }

    @Override
    public void delete(Long id) {
        getById(id);
        orderRepository.deleteById(id);
        logger.info("delete order by id: {}", id);
    }

    @Override
    public List<Order> getAll() {
        logger.info("get all orders");
        return orderRepository.findAll();
    }
}
