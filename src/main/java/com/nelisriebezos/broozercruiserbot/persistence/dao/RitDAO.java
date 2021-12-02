package com.nelisriebezos.broozercruiserbot.persistence.dao;


import com.nelisriebezos.broozercruiserbot.domain.Trip;

import java.util.List;

public interface RitDAO {
    public boolean save(Trip trip);
    public boolean update(Trip trip);
    public boolean delete(Trip trip);
    public Trip findById(int id);
    public List<Trip> findAll();
}

