package com.nelisriebezos.broozercruiserbot.persistence.dao;


import com.nelisriebezos.broozercruiserbot.domain.Person;

import java.util.List;

public interface ChauffeurDAO {
    public boolean save(Person person);
    public boolean update(Person person);
    public boolean delete(Person person);
    public Person findById(int id);
    public List<Person> findAll();
}
