package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TankSessionRepository extends JpaRepository<TankSession, Long> {
}
