package com.mera.inkrot.carshowroom.rest;

import com.mera.inkrot.carshowroom.model.Customer;
import com.mera.inkrot.carshowroom.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/customer")
public class CustomerRestController {

    Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "this is test";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long customerId) {
        if (customerId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Customer customer = customerService.getById(customerId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
