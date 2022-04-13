package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
