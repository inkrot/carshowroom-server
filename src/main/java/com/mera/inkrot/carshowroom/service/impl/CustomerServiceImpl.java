package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Customer;
import com.mera.inkrot.carshowroom.repository.CustomerRepository;
import com.mera.inkrot.carshowroom.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        logger.info("get customer by id: {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        if(! customer.isPresent())
            throw new IllegalArgumentException("Customer not found");
        return customer.get();
    }
}
