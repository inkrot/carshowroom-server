package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
