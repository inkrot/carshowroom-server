package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Brand;
import com.mera.inkrot.carshowroom.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand getBrandByName(String name);
}
