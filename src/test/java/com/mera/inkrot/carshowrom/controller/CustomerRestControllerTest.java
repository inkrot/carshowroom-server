package com.mera.inkrot.carshowrom.controller;

import com.mera.inkrot.carshowrom.util.JsonFormatter;
import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.rest.CustomerRestController;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRestController customerRestController;

    private static final String BASE_URL = "/rest/customer/";

    @Test
    public void getOrderTest() throws Exception {
        Long id = 1L;
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(id);
        ResponseEntity<CustomerDto> re = new ResponseEntity<>(customerDto, HttpStatus.OK);
        given(customerRestController.getCustomer(customerDto.getId())).willReturn(re);
        mvc.perform(get(BASE_URL + id)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonFormatter.getJsonString(customerDto)));
    }
}
