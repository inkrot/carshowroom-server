package com.mera.inkrot.carshowroom.service.impl;

import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.repository.BrandRepository;
import com.mera.inkrot.carshowroom.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private BrandRepository brandRepository;

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
}
