package com.mera.inkrot.carshowroom.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "options")
public class Option extends BaseEntity {

    private String name;

    //@ManyToMany(mappedBy = "options", fetch = FetchType.LAZY)
    //private Set<Order> orders = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }*/
}
