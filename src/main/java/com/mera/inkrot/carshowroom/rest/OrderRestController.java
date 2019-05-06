package com.mera.inkrot.carshowroom.rest;

import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.*;
import com.mera.inkrot.carshowroom.service.CarService;
import com.mera.inkrot.carshowroom.service.CustomerService;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rest/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long orderId) {
        if (orderId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.getById(orderId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    /*@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order) {
        if (order == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        customerService.save(order.getCustomer());
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Order> addOrder(@RequestBody /*warning ->*/ @Valid OrderDto orderDto) {
        orderService.save(orderDto);
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
