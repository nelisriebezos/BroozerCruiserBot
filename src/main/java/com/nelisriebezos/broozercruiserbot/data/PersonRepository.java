package com.nelisriebezos.broozercruiserbot.data;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
