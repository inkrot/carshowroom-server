package com.mera.inkrot.carshowrom.service;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.model.Option;
import com.mera.inkrot.carshowroom.repository.OptionRepository;
import com.mera.inkrot.carshowroom.service.OptionService;
import com.mera.inkrot.carshowroom.service.impl.OptionServiceImpl;
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
public class OptionServiceTest {

    @Autowired
    private OptionRepository optionRepository;

    private OptionService optionService;

    @Before
    public void setUp() {
        optionService = new OptionServiceImpl(optionRepository);
    }

    @Test
    public void whenGetByIdThenReturnOption() {
        //given
        Long id = 1L;

        //when
        Option obtained = optionService.getById(id);

        //then
        assertThat(obtained.getId(), is(equalTo(id)));
    }
}
