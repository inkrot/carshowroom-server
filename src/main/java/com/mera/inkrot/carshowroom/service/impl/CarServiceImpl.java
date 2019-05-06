package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.model.Car;
import com.mera.inkrot.carshowroom.repository.CarRepository;
import com.mera.inkrot.carshowroom.service.BrandService;
import com.mera.inkrot.carshowroom.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CarServiceImpl implements CarService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private BrandService brandService;

    @Override
    public Car save(String modelName, String brandName) {
        if (modelName == null)
            throw new IllegalArgumentException("modelName do not entered");
        if (brandName == null)
            throw new IllegalArgumentException("brandName do not entered");
        Collection<Car> findCar = carRepository.getCarByModelAndBrand(modelName, brandName);
        if (findCar.iterator().hasNext()) {
            logger.debug("Car {} - {} already exist", modelName, brandName);
            return findCar.iterator().next();
        }
        Brand brand = brandService.save(brandName);
        logger.info("save car: {} - {}", modelName, brandName);
        return carRepository.saveAndFlush(new Car(modelName, brand));
    }
}
