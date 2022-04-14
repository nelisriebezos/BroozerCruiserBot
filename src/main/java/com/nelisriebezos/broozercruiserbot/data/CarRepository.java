package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistCarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepository extends JpaRepository<PersistCarDTO, Long> {
    @Query(nativeQuery = true, value = "SELECT id FROM car c where c.name = :name")
    Long findCarIdByName(String name);
}
