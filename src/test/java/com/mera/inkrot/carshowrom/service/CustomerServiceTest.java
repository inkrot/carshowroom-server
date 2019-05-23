package com.mera.inkrot.carshowrom.service;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.dto.CustomerDto;
import com.mera.inkrot.carshowroom.model.Customer;
import com.mera.inkrot.carshowroom.repository.CustomerRepository;
import com.mera.inkrot.carshowroom.service.CustomerService;
import com.mera.inkrot.carshowroom.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void whenSaveThenReturnSavedCustomerDto() {
        //given
        String name = "Test Customer";

        //when
        CustomerDto saved = customerService.save(name);

        //then
        assertThat(saved.getName(), is(equalTo(name)));
    }

    @Test
    public void whenGetByIdThenReturnCustomerDto() {
        //given
        Long id = customerRepository.save(new Customer("Customer Name")).getId();

        //when
        CustomerDto obtained = customerService.getById(id);

        //then
        assertThat(obtained.getId(), is(equalTo(id)));
    }

    @Test
    public void whenGetByIdOrNameThenReturnCustomer() {
        //given
        Long id = null;
        String name = "Customer Name";
        customerRepository.save(new Customer(name));

        //when
        Customer obtained = customerService.getByIdOrName(id, name);

        //then
        assertThat(obtained.getName(), is(equalTo(name)));
    }
}
