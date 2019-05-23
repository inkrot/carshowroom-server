package com.mera.inkrot.carshowrom.service;

import com.mera.inkrot.carshowroom.Application;
import com.mera.inkrot.carshowroom.model.Car;
import com.mera.inkrot.carshowroom.repository.BrandRepository;
import com.mera.inkrot.carshowroom.repository.CarRepository;
import com.mera.inkrot.carshowroom.service.BrandService;
import com.mera.inkrot.carshowroom.service.CarService;
import com.mera.inkrot.carshowroom.service.impl.BrandServiceImpl;
import com.mera.inkrot.carshowroom.service.impl.CarServiceImpl;
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
public class CarServiceTest {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CarRepository carRepository;

    private BrandService brandService;
    private CarService carService;

    @Before
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository, carRepository);
        carService = new CarServiceImpl(carRepository, brandService);
    }

    @Test
    public void whenSaveThenReturnSavedCar() {
        //given
        String modelName = "Model";
        String brandName = "Brand";

        //when
        Car saved = carService.save(modelName, brandName);

        //then
        assertThat(saved.getModel(), is(equalTo(modelName)));
        assertThat(saved.getBrand().getName(), is(equalTo(brandName)));
    }
}
