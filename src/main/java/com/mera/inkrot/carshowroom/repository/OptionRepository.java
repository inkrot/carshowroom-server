package com.mera.inkrot.carshowroom.repository;

import com.mera.inkrot.carshowroom.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {
}
