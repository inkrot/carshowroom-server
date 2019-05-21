package com.mera.inkrot.carshowrom.old_service_test;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.dto.OrderDto;
import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class OrderServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @MockBean
    private OrderService orderService;

    @Test
    public void whenGetById() {
        //given
        Order order = new Order();
        entityManager.persistAndFlush(order);

        //when
        OrderDto testOrder = orderService.getById(order.getId());

        System.out.println("testOrder: " + testOrder);
        System.out.println("order: " + order.getId());
        //then
        assertThat(testOrder).isEqualTo(OrderDto.getFromEntity(order));
    }
}
