package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
