package com.mera.inkrot.carshowrom;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.dto.StatusDto;
import com.mera.inkrot.carshowroom.model.*;
import com.mera.inkrot.carshowroom.repository.*;
import com.mera.inkrot.carshowroom.service.*;
import com.mera.inkrot.carshowroom.service.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

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

    @Test
    public void whenUpdateOrderDtoThenReturnSavedOrderDto() {
        //given
        Order order = new Order();
        order.setCustomer(new Customer("Customer"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        Long id = orderRepository.saveAndFlush(order).getId();
        OrderDto orderDto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Updated Customer");
        orderDto.setCustomer(customerDto);
        orderDto.setModelName("Updated Model");
        orderDto.setBrandName("Updated Brand");
        orderDto.setStatus(new StatusDto(1L));

        //when
        OrderDto updated = orderService.update(id, orderDto);

        //then
        assertThat(updated.getCustomer(), is(equalTo(orderDto.getCustomer())));
        assertThat(updated.getModelName(), is(equalTo(orderDto.getModelName())));
        assertThat(updated.getBrandName(), is(equalTo(orderDto.getBrandName())));
        assertThat(updated.getStatus().getId(), is(equalTo(orderDto.getStatus().getId())));
    }

    @Test
    public void whenDeleteThenReturnString() {
        //given
        Order order = new Order();
        order.setCustomer(new Customer("Test Customer"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        Long id = orderRepository.saveAndFlush(order).getId();

        //when
        String str = orderService.delete(id);

        //then
        assertThat(str, is(equalTo("Deleted")));
    }

    @Test
    public void whenGetAllThenReturnOrderDtoList() {
        //given
        Order order = new Order();
        order.setCustomer(new Customer("Test Customer"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        Order saved = orderRepository.saveAndFlush(order);
        List<OrderDto> savedOrders = Collections.singletonList(OrderDto.getFromEntity(saved));

        //when
        List<OrderDto> orders = orderService.getAll();

        //then
        assertThat(orders, is(equalTo(savedOrders)));
    }

    @Test
    public void whenGetAllByStatusAndCustomerThenReturnOrderDtoList() {
        //given
        StatusDto statusDto = new StatusDto(1L);
        CustomerDto customerDto = new CustomerDto("Search Customer");
        Order order = new Order();
        order.setCustomer(new Customer(customerDto.getName()));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(statusDto.getId()));
        Order saved = orderRepository.saveAndFlush(order);
        List<OrderDto> savedOrders = Collections.singletonList(OrderDto.getFromEntity(saved));

        //when
        List<OrderDto> orders = orderService.getAllByStatusAndCustomer(statusDto, customerDto);

        //then
        assertThat(orders, is(equalTo(savedOrders)));
    }

    @Test
    public void whenGetByIdThenReturnOrderDto() {
        //given
        Order order = new Order();
        order.setCustomer(new Customer("Test Customer"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        Long id = orderRepository.saveAndFlush(order).getId();

        //when
        OrderDto obtained = orderService.getById(id);

        //then
        assertThat(obtained, is(equalTo(OrderDto.getFromEntity(order))));
    }

    @Test
    public void whenAddOptionThenAddOptionToOrder() {
        //given
        Option option = optionRepository.getOne(1L);
        Order order = new Order();
        order.setCustomer(new Customer("Test Customer"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        Order saved = orderRepository.saveAndFlush(order);

        //when
        orderService.addOption(saved, option);

        //then
        assertThat(orderRepository.getOne(saved.getId()).getOptions().contains(option), is(true));
    }

    @Test
    public void whenSetOptionsThenSetOptionsToOrder() {
        //given
        Set<Option> options = Collections.singleton(optionRepository.getOne(1L));
        Order order = new Order();
        order.setCustomer(new Customer("Test Customer"));
        order.setCar(new Car("Model", new Brand("Brand")));
        order.setStatus(statusRepository.getOne(1L));
        Order saved = orderRepository.saveAndFlush(order);

        //when
        orderService.setOptions(saved, options);

        //then
        assertThat(orderRepository.getOne(saved.getId()).getOptions(), is(equalTo(options)));
    }
}
