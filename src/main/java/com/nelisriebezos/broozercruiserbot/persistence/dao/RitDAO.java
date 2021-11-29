package com.nelisriebezos.broozercruiserbot.persistence.dao;


import com.nelisriebezos.broozercruiserbot.domain.Rit;

import java.util.List;

public interface RitDAO {
    public boolean save(Rit rit);
    public boolean update(Rit rit);
    public boolean delete(Rit rit);
    public Rit findById(int id);
    public List<Rit> findAll();
}

