package com.mera.inkrot.carshowrom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.dto.StatusCustomerDto;
import com.mera.inkrot.carshowroom.dto.StatusDto;
import com.mera.inkrot.carshowroom.rest.OrderRestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(OrderRestController.class)
public class OrderRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderRestController orderRestController;

    private static final String BASE_URL = "/rest/order/";

    public static String getJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void saveOrderTest() throws Exception {
        OrderDto orderDto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Test Customer");
        orderDto.setCustomer(customerDto);
        orderDto.setModelName("test model");
        orderDto.setBrandName("test brand");
        orderDto.setOptions(Collections.emptySet());
        ResponseEntity<OrderDto> re = new ResponseEntity<>(orderDto, HttpStatus.CREATED);
        given(orderRestController.saveOrder(orderDto)).willReturn(re);
        mvc.perform(post(BASE_URL)
                .content(getJsonString(orderDto))
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .accept(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().json(getJsonString(orderDto)));
    }

    @Test
    public void updateOrderTest() throws Exception {
        Long id = 1L;
        OrderDto orderDto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("New Customer");
        orderDto.setCustomer(customerDto);
        orderDto.setModelName("new model");
        orderDto.setBrandName("new brand");
        orderDto.setOptions(Collections.emptySet());
        ResponseEntity<OrderDto> re = new ResponseEntity<>(orderDto, HttpStatus.OK);
        given(orderRestController.updateOrder(id, orderDto)).willReturn(re);
        mvc.perform(post(BASE_URL + id)
                .content(getJsonString(orderDto))
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .accept(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonString(orderDto)));
    }

    @Test
    public void deleteOrderTest() throws Exception {
        Long id = 1L;
        ResponseEntity<String> re = new ResponseEntity<>("Deleted", HttpStatus.OK);
        given(orderRestController.deleteOrder(id)).willReturn(re);
        mvc.perform(delete(BASE_URL + id)
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(re.getBody()));
    }

    @Test
    public void getAllOrdersTest() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(1L);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Test Customer");
        orderDto.setCustomer(customerDto);
        orderDto.setModelName("test model");
        orderDto.setBrandName("test brand");
        orderDto.setOptions(Collections.emptySet());
        List<OrderDto> orders = Collections.singletonList(orderDto);
        ResponseEntity<List<OrderDto>> re = new ResponseEntity<>(orders, HttpStatus.OK);
        given(orderRestController.getAllOrders()).willReturn(re);
        mvc.perform(get(BASE_URL + "all")
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(orders.get(0).getId()))
                .andExpect(jsonPath("$[0].customer").value(orders.get(0).getCustomer()))
                .andExpect(jsonPath("$[0].modelName").value(orders.get(0).getModelName()))
                .andExpect(jsonPath("$[0].brandName").value(orders.get(0).getBrandName()))
                .andExpect(jsonPath("$[0].status").value(orders.get(0).getStatus()));
    }

    @Test
    public void getAllOrdersByStatusAndCustomerTest() throws Exception {
        OrderDto orderDto = new OrderDto();
        StatusCustomerDto statusCustomerDto = new StatusCustomerDto();
        StatusDto statusDto = new StatusDto();
        statusDto.setId(1L);
        statusCustomerDto.setStatus(statusDto);
        orderDto.setStatus(statusDto);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Test Customer");
        statusCustomerDto.setCustomer(customerDto);
        orderDto.setCustomer(customerDto);
        List<OrderDto> orders = Collections.singletonList(orderDto);
        ResponseEntity<List<OrderDto>> re = new ResponseEntity<>(orders, HttpStatus.OK);
        given(orderRestController.getAllOrdersByStatusAndCustomer(statusCustomerDto)).willReturn(re);
        mvc.perform(post(BASE_URL + "allByStatusAndCustomer")
                .content(getJsonString(statusCustomerDto))
                .contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(orders.get(0).getId()))
                .andExpect(jsonPath("$[0].customer").value(orders.get(0).getCustomer()))
                .andExpect(jsonPath("$[0].modelName").value(orders.get(0).getModelName()))
                .andExpect(jsonPath("$[0].brandName").value(orders.get(0).getBrandName()))
                .andExpect(jsonPath("$[0].status").value(orders.get(0).getStatus()));
    }

    @Test
    public void getOrderTest() throws Exception {
        Long id = 1L;
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        ResponseEntity<OrderDto> re = new ResponseEntity<>(orderDto, HttpStatus.OK);
        given(orderRestController.getOrder(orderDto.getId())).willReturn(re);
        mvc.perform(get(BASE_URL + id)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonString(orderDto)));
    }
}
