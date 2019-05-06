package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer getCustomerByName(String name);
}
