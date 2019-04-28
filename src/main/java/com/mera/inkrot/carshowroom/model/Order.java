package com.mera.inkrot.carshowroom.model;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private HashSet<OrderOption> options;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public HashSet<OrderOption> getOptions() {
        return options;
    }

    public void setOptions(HashSet<OrderOption> options) {
        this.options = options;
    }
}
