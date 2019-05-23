package com.mera.inkrot.carshowroom.rest;

import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.dto.StatusCustomerDto;
import com.mera.inkrot.carshowroom.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/order")
@Api(description = "Set of endpoints for save, update, delete, get the orders.")
public class OrderRestController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation("Save Order to the System.")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.CREATED);
    }

    @ApiOperation("Update Order in the System.")
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@ApiParam("Id of the Order to be updated.") @PathVariable("id") Long orderId, @ApiParam("Order Data Transfer Object.") @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.update(orderId, orderDto), HttpStatus.OK);
    }

    @ApiOperation("Delete Order from the System.")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> deleteOrder(@ApiParam("Id of the Order to be deleted.") @PathVariable("id") Long orderId) {
        if (orderId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderService.delete(orderId), HttpStatus.OK);
    }

    @ApiOperation("Returns list of all Orders in the system.")
    @RequestMapping(value = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @ApiOperation("Returns list of all Orders in the system by Status and Customer.")
    @RequestMapping(value = "allByStatusAndCustomer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrdersByStatusAndCustomer(@ApiParam("Status and Customer Data Transfer Objects.") @RequestBody StatusCustomerDto statusCustomerDto) {
        return new ResponseEntity<>(orderService.getAllByStatusAndCustomer(statusCustomerDto.getStatus(), statusCustomerDto.getCustomer()), HttpStatus.OK);
    }

    @ApiOperation("Get Order in the System.")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<OrderDto> getOrder(@ApiParam("Id of the Order to be obtained.") @PathVariable("id") Long orderId) {
        if (orderId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        OrderDto orderDto = orderService.getById(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
}
