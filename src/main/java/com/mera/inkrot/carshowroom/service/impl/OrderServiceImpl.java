package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OptionDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.dto.StatusDto;
import com.mera.inkrot.carshowroom.model.*;
import com.mera.inkrot.carshowroom.repository.CustomerRepository;
import com.mera.inkrot.carshowroom.repository.OrderRepository;
import com.mera.inkrot.carshowroom.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.*;

@WebService
@Component
@org.apache.cxf.interceptor.InFaultInterceptors (interceptors = {"com.mera.inkrot.carshowroom.handler.SoapInterceptor" })
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private CarService carService;

    private OptionService optionService;

    private StatusService statusService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, CustomerService customerService, CarService carService, OptionService optionService, StatusService statusService) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.carService = carService;
        this.optionService = optionService;
        this.statusService = statusService;
    }

    public OrderServiceImpl() {
    }

    private OrderDto saveOrUpdate(Long id, OrderDto orderDto) {
        Set<Option> options = new HashSet<>();
        if (orderDto.getOptions() == null) orderDto.setOptions(Collections.EMPTY_SET);
        for (OptionDto optionDto : orderDto.getOptions())
            options.add(optionService.getById(optionDto.getId()));
        String customerName = orderDto.getCustomer() == null ? "" : orderDto.getCustomer().getName();
        Customer customer = customerRepository.saveAndFlush(new Customer(customerName));
        Car car = carService.save(orderDto.getModelName(), orderDto.getBrandName());
        Status status = statusService.getById(orderDto.getStatus().getId());
        Order order = orderRepository.saveAndFlush(new Order(id, customer, car, status));
        setOptions(order, options);
        return OrderDto.getFromEntity(order);
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        if (orderDto == null) orderDto = new OrderDto();
        orderDto.setStatus(StatusDto.getFromEntity(statusService.getById(1L)));
        OrderDto order = saveOrUpdate(null, orderDto);
        logger.info("save order: {}", order.getId());
        return order;
    }

    @Override
    public OrderDto update(Long id, OrderDto orderDto) {
        OrderDto findOrder = getById(id);
        if (orderDto.getCustomer().getName() != null)
            findOrder.getCustomer().setName(orderDto.getCustomer().getName());
        if (orderDto.getModelName() != null)
            findOrder.setModelName(orderDto.getModelName());
        if (orderDto.getBrandName() != null)
            findOrder.setBrandName(orderDto.getBrandName());
        StatusDto status = orderDto.getStatus();
        if (status != null)
            if (status.getId() != null)
                findOrder.setStatus(status);
        Set<OptionDto> options = orderDto.getOptions();
        if (options != null)
            if (options.size() > 0)
                findOrder.setOptions(orderDto.getOptions());
        OrderDto order = saveOrUpdate(id, findOrder);
        logger.info("update order: {}", order.getId());
        return order;
    }

    @Override
    public String delete(Long id) {
        getById(id);
        orderRepository.deleteById(id);
        logger.info("delete order by id: {}", id);
        return "Deleted";
    }

    @Override
    public List<OrderDto> getAll() {
        logger.info("get all orders");
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderRepository.findAll())
            orderDtoList.add(OrderDto.getFromEntity(order));
        return orderDtoList;
    }

    @Override
    public List<OrderDto> getAllByStatusAndCustomer(StatusDto statusDto, CustomerDto customerDto) {
        logger.info("get orders by status: {} & customer: {}", statusDto, customerDto);
        Status status = statusService.getByIdOrCode(statusDto.getId(), statusDto.getCode());
        Customer customer = customerService.getByIdOrName(customerDto.getId(), customerDto.getName());
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderRepository.findAllByStatusAndCustomer(status, customer))
            orderDtoList.add(OrderDto.getFromEntity(order));
        return orderDtoList;
    }

    @Override
    public OrderDto getById(Long id) {
        logger.info("get order by id: {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent())
            throw new IllegalArgumentException("Order not found");
        return OrderDto.getFromEntity(order.get());
    }

    @Override
    public void addOption(Order order, Option option) {
        order.getOptions().add(option);
        orderRepository.save(order);
    }

    @Override
    public void setOptions(Order order, Set<Option> options) {
        for (Option option : options)
            addOption(order, option);
    }
}
