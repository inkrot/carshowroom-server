package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.*;
import com.mera.inkrot.carshowroom.repository.OrderRepository;
import com.mera.inkrot.carshowroom.service.CarService;
import com.mera.inkrot.carshowroom.service.CustomerService;
import com.mera.inkrot.carshowroom.service.OptionService;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    CarService carService;

    @Autowired
    OptionService optionService;

    @Override
    public Order getById(Long id) {
        logger.info("get order by id: {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if(! order.isPresent())
            throw new IllegalArgumentException("Order not found");
        return order.get();
    }

    @Override
    public void save(OrderDto orderDto) {
        Set<Option> options = new HashSet<>();
        for (Long optionId : orderDto.getOptions())
            options.add(optionService.getById(optionId));
        Customer customer = customerService.save(orderDto.getCustomerName());
        Car car = carService.save(orderDto.getModelName(), orderDto.getBrandName());
        Order order = orderRepository.save(new Order(customer, car, new OrderStatus(1L)));
        setOptions(order, options);
        logger.info("save order: {}", order.getId());
    }

    @Override
    public void addOption(Order order, Option option) {
        order.getOptions().add(option);
        orderRepository.save(order);
    }

    @Override
    public void setOptions(Order order, Set<Option> options) {
        order.setOptions(options);
        orderRepository.save(order);
    }

    @Override
    public void removeOption(Order order, Option option) {
        // TODO
    }

    @Override
    public List<Option> getOptions(Long orderId) {
        // TODO
        return null;
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
