package com.sokolowska.travelplannerapi.model.mapper;

import com.sokolowska.travelplannerapi.model.Airport;
import com.sokolowska.travelplannerapi.api.dto.AirportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    @Mapping(source = "location.geoApiPlaceId", target = "geoApiPlaceId")
    AirportDto toDto(Airport airport);

    Airport toEty(AirportDto airportDto);

    List<Airport> toEtyList(List<AirportDto> airportDtos);

    List<AirportDto> toDtoList(List<Airport> airports);
}
