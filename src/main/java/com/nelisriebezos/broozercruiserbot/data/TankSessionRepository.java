package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TankSessionRepository extends JpaRepository<PersistTankSessionDTO, Long> {
}
