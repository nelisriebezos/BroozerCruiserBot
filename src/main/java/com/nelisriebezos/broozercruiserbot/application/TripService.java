package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.data.TripRepository;
import com.nelisriebezos.broozercruiserbot.data.dto.mapper.PersistTripDTOMapper;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class TripService implements ITripService {
    private final TripRepository tripRepository;
    private final PersistTripDTOMapper tripDTOMapper;

    @Override
    public Trip getTrip(Long id) {
        return this.tripDTOMapper.fromDTO(this.tripRepository.getById(id));
    }

    @Override
    public Trip persistTrip(Trip trip) {
        return this.tripDTOMapper.fromDTO(this.tripRepository.save(tripDTOMapper.toDTO(trip)));
    }

    @Override
    public boolean deleteTrip(Trip trip) {
        this.tripRepository.delete(tripDTOMapper.toDTO(trip));
        return true;
    }
}
