package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTripDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<PersistTripDTO, Long> {
}
