package com.mera.inkrot.carshowrom;

import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.repository.OrderRepository;
import com.mera.inkrot.carshowroom.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void whenGetByIdThenReturnOrderDto() {
        Long id = 1L;

        when(orderRepository.getOne(anyLong())).thenReturn(new Order());

        OrderDto obtained = orderService.getById(id);

        assertThat(obtained.getId()).isSameAs(id);
    }

    @Test
    public void whenSaveOrderDtoThenReturnSavedOrderDto() {
        OrderDto orderDto = new OrderDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("New Customer");
        orderDto.setCustomer(customerDto);
        orderDto.setModelName("new model");
        orderDto.setBrandName("new brand");
        orderDto.setOptions(Collections.emptySet());

        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        OrderDto saved = orderService.save(orderDto);

        assertThat(saved).isSameAs(orderDto);
    }
}
