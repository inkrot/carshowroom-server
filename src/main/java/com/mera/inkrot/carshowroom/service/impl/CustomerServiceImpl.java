package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.model.Customer;
import com.mera.inkrot.carshowroom.repository.CustomerRepository;
import com.mera.inkrot.carshowroom.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.Optional;

@WebService
@Component
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerServiceImpl() {
    }

    @Override
    public CustomerDto save(String name) {
        if (name == null)
            throw new IllegalArgumentException("Customer name do not entered");
        Optional<Customer> customer = customerRepository.findByName(name);
        if (customer.isPresent()) {
            logger.debug("Customer {} already exist", name);
            return CustomerDto.getFromEntity(customer.get());
        }
        logger.info("save customer: {}", name);
        return CustomerDto.getFromEntity(customerRepository.saveAndFlush(new Customer(name)));
    }

    @Override
    public CustomerDto getById(Long id) {
        logger.info("get customer by id: {}", id);
        Optional<Customer> customer = customerRepository.findById(id);
        if (! customer.isPresent())
            throw new IllegalArgumentException("Customer not found");
        return CustomerDto.getFromEntity(customer.get());
    }

    @Override
    public Customer getByIdOrName(Long id, String name) {
        logger.info("get customer by id: {} or name {}", id, name);
        Optional<Customer> customerById = (id == null ? Optional.empty() : customerRepository.findById(id));
        if (! customerById.isPresent()) {
            Optional<Customer> customerByName = (name == null ? Optional.empty() : customerRepository.findByName(name));
            if (! customerByName.isPresent())
                throw new IllegalArgumentException("Customer not found");
            return customerByName.get();
        }
        return customerById.get();
    }
}
