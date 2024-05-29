package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.domain.Trip;

public interface ITripService {
    public Trip getTrip(Long id);
    public Trip persistTrip(Trip trip);
    public boolean deleteTrip(Trip trip);
}
