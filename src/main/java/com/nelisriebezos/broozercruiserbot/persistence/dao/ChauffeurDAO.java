package com.nelisriebezos.broozercruiserbot.persistence.dao;


import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;

import java.util.List;

public interface ChauffeurDAO {
    public boolean save(Chauffeur chauffeur);
    public boolean update(Chauffeur chauffeur);
    public boolean delete(Chauffeur chauffeur);
    public Chauffeur findById(int id);
    public List<Chauffeur> findAll();
}
