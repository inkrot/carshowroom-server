package com.mera.inkrot.carshowroom.rest;

import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

    Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
        if (orderId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Order order = orderService.getById(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> saveOrder(@RequestBody @Valid Order order) {
        if (order == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        orderService.save(order);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long orderId) {
        if (orderId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        orderService.delete(orderId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
