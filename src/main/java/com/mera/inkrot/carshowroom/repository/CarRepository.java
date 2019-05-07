package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.model=:model AND c.brand.name=:brand")
    Collection<Car> getCarByModelAndBrand(@Param("model") String model, @Param("brand") String brand);

    @Modifying
    @Transactional
    @Query("DELETE FROM Car c WHERE c.brand.id=:id")
    void deleteCarByBrandId(@Param("id") Long id);
}
