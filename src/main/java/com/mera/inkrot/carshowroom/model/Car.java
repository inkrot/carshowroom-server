package com.mera.inkrot.carshowroom.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    private String model;

    @JoinColumn(name = "brand_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    public Car() {
    }

    public Car(String model, Brand brand) {
        this.model = model;
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
