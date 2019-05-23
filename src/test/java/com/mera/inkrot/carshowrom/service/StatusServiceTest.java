package com.mera.inkrot.carshowrom.service;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.model.Status;
import com.mera.inkrot.carshowroom.repository.StatusRepository;
import com.mera.inkrot.carshowroom.service.StatusService;
import com.mera.inkrot.carshowroom.service.impl.StatusServiceImpl;
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
public class StatusServiceTest {

    @Autowired
    private StatusRepository statusRepository;

    private StatusService statusService;

    @Before
    public void setUp() {
        statusService = new StatusServiceImpl(statusRepository);
    }

    @Test
    public void whenGetByIdThenReturnStatus() {
        //given
        Long id = 1L;

        //when
        Status obtained = statusService.getById(id);

        //then
        assertThat(obtained.getId(), is(equalTo(id)));
    }

    @Test
    public void whenGetByIdOrCodeThenReturnStatus() {
        //given
        Long id = null;
        String code = "BEING_PROCESSED";

        //when
        Status obtained = statusService.getByIdOrCode(id, code);

        //then
        assertThat(obtained.getCode(), is(equalTo(code)));
    }
}