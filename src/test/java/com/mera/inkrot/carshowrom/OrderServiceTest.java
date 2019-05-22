package com.mera.inkrot.carshowrom;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.model.Car;
import com.mera.inkrot.carshowroom.model.Customer;
import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.repository.*;
import com.mera.inkrot.carshowroom.service.*;
import com.mera.inkrot.carshowroom.service.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private OptionRepository optionRepository;

    private CustomerService customerService;
    private BrandService brandService;
    private CarService carService;
    private OptionService optionService;
    private StatusService statusService;
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
        brandService = new BrandServiceImpl(brandRepository, carRepository);
        carService = new CarServiceImpl(carRepository, brandService);
        optionService = new OptionServiceImpl(optionRepository);
        statusService = new StatusServiceImpl(statusRepository);
        orderService = new OrderServiceImpl(orderRepository, customerRepository, customerService, carService, optionService, statusService);
    }

    @Test
    public void whenGetByIdThenReturnOrderDto() {
        //given
        Order order = new Order();
        order.setCustomer(new Customer("Hello"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        orderRepository.save(order);

        //when
        OrderDto obtained = orderService.getById(order.getId());

        //then
        assertThat(obtained, is(equalTo(OrderDto.getFromEntity(order))));
    }

    @Test
    public void whenSaveOrderDtoThenReturnSavedOrderDto() {
        //given
        OrderDto orderDto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("New Customer");
        orderDto.setCustomer(customerDto);
        orderDto.setModelName("new model");
        orderDto.setBrandName("new brand");
        orderDto.setOptions(Collections.emptySet());

        //when
        OrderDto saved = orderService.save(orderDto);

        //then
        assertThat(saved, is(equalTo(orderDto)));
    }
}
