package com.mera.inkrot.carshowroom.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "order")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
    }, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_options",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    @XmlElementWrapper(name = "options")
    @XmlElement(name = "option")
    private Set<Option> options = new HashSet<>();

    public Order() {
    }

    public Order(Customer customer, Car car, OrderStatus status) {
        this.customer = customer;
        this.car = car;
        this.status = status;
    }

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

    public Set<Option> getOptions() {
        return options;
    }

}
