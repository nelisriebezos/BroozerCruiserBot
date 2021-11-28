package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.domain.TankSession;

public interface TankSessionDAO {
    public boolean save(TankSession tankSession);
    public boolean update(TankSession tankSession);
    public boolean delete(TankSession tankSession);
    public TankSession findById(int id);
}
