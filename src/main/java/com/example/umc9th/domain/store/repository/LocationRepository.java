package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findLocationById(Long id);
}
