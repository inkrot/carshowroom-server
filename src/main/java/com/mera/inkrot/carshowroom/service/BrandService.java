package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.model.Brand;

public interface BrandService {

    Brand getById(Long id);

    Brand save(String name);

    String delete(Long id);
}