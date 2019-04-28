package com.mera.inkrot.carshowroom.model;

import javax.persistence.*;

@Entity
@Table(name = "orders_options")
public class OrderOption {

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "option_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;
}
