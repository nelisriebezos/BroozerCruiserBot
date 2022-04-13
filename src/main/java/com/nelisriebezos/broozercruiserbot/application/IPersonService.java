package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.domain.Person;

public interface IPersonService {
    public Person getPerson(Long id);
    public Person persistPerson(Person person);
    public boolean deletePerson(Person person);
}
