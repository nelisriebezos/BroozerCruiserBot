package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
