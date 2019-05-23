package com.mera.inkrot.carshowrom.service;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.repository.BrandRepository;
import com.mera.inkrot.carshowroom.repository.CarRepository;
import com.mera.inkrot.carshowroom.service.BrandService;
import com.mera.inkrot.carshowroom.service.impl.BrandServiceImpl;
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
public class BrandServiceTest {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CarRepository carRepository;

    private BrandService brandService;

    @Before
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository, carRepository);
    }

    @Test
    public void whenGetByIdThenReturnBrand() {
        //given
        Long id = brandRepository.saveAndFlush(new Brand("New Brand")).getId();

        //when
        Brand obtained = brandService.getById(id);

        //then
        assertThat(obtained.getId(), is(equalTo(id)));
    }

    @Test
    public void whenSaveThenReturnSavedBrand() {
        //given
        String name = "Model";

        //when
        Brand saved = brandService.save(name);

        //then
        assertThat(saved.getName(), is(equalTo(name)));
    }

    @Test
    public void whenDeleteThenReturnString() {
        //given
        Long id = brandRepository.save(new Brand("New Brand")).getId();

        //when
        String str = brandService.delete(id);

        //then
        assertThat(str, is(equalTo("Deleted")));
    }
}
