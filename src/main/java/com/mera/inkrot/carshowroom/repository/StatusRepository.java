package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Optional<Status> findByCode(String code);
}