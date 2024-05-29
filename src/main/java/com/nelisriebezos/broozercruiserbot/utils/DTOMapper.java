package com.nelisriebezos.broozercruiserbot.utils;

import java.util.List;
import java.util.stream.Collectors;

public interface DTOMapper<DTOO extends DTO, O> {
    public DTOO toDTO(O o);
    public O fromDTO(DTOO o);
    public default List<DTOO> toMultipleDTO(List<O> objects) {
        return objects.stream().map(this::toDTO).collect(Collectors.toList());
    }
    public default List<O> fromMultipleDTO(List<DTOO> objects) {
        return objects.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}
