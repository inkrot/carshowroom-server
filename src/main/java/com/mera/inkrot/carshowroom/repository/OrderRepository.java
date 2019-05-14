package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Customer;
import com.mera.inkrot.carshowroom.model.Order;
import com.mera.inkrot.carshowroom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatusAndCustomer(Status status, Customer customer);
}
