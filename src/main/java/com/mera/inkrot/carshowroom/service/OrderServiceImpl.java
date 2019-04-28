package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order getById(Long id) {
        logger.info("get order by id: {}", id);
        return orderRepository.getOne(id);
    }

    @Override
    public void save(Order order) {
        logger.info("save order: {}", order);
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        logger.info("delete order by id: {}", id);
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAll() {
        logger.info("get all orders");
        return orderRepository.findAll();
    }
}
