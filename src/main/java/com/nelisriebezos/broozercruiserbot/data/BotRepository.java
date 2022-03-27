package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepository extends JpaRepository<Car, Long> {
}
