package com.sokolowska.airport.impl.model.model.mapper;


import com.sokolowska.airport.api.dto.LocationDto;
import com.sokolowska.airport.impl.model.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto mapToDto(Location domain);

    Location mapToDomain(LocationDto dto);
}
