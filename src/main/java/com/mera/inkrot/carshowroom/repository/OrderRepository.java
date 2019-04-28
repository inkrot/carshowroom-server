package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
