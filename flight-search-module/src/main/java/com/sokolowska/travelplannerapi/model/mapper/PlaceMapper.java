package com.sokolowska.travelplannerapi.model.mapper;

import com.sokolowska.travelplannerapi.model.Place;
import com.sokolowska.travelplannerapi.model.dto.PlaceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaceMapper {

    PlaceDto mapToDto(Place domain);

    Place mapToDomain(PlaceDto dto);
}
