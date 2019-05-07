package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.repository.BrandRepository;
import com.mera.inkrot.carshowroom.repository.CarRepository;
import com.mera.inkrot.carshowroom.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Brand getById(Long id) {
        logger.info("get brand by id: {}", id);
        Optional<Brand> brand = brandRepository.findById(id);
        if(! brand.isPresent())
            throw new IllegalArgumentException("Brand not found");
        return brand.get();
    }

    @Override
    public Brand save(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Brand name do not entered");
        }
        Brand findBrand = brandRepository.getBrandByName(name);
        if (findBrand != null) {
            logger.debug("Brand {} already exist", name);
            return findBrand;
        }
        logger.info("save brand: {}", name);
        return brandRepository.saveAndFlush(new Brand(name));
    }

    @Override
    public void delete(Long id) {
        getById(id);
        carRepository.deleteCarByBrandId(id);
        brandRepository.deleteById(id);
        logger.info("delete brand by id: {}", id);
    }
}
