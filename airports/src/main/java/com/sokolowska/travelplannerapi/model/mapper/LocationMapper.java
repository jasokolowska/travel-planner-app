package com.sokolowska.travelplannerapi.model.mapper;


import com.sokolowska.travelplannerapi.api.dto.LocationDto;
import com.sokolowska.travelplannerapi.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    LocationDto mapToDto(Location domain);

    Location mapToDomain(LocationDto dto);
}
