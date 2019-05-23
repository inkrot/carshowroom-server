package com.mera.inkrot.carshowroom.service;

import com.mera.inkrot.carshowroom.model.Car;

public interface CarService {
    Car save(String modelName, String brandName);
}
