package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistCarDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<PersistCarDTO, Long> {
}
