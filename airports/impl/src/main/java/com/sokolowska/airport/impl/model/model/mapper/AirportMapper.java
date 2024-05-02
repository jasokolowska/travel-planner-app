package com.sokolowska.airport.impl.model.model.mapper;

import com.sokolowska.airport.impl.model.model.dto.AirportDto;
import com.sokolowska.airport.impl.model.model.Airport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportDto toDto(Airport airport);

    Airport toEty(AirportDto airportDto);
}
