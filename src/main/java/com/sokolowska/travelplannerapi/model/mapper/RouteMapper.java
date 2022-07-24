package com.sokolowska.travelplannerapi.model.mapper;

import com.sokolowska.travelplannerapi.model.Place;
import com.sokolowska.travelplannerapi.model.Route;
import com.sokolowska.travelplannerapi.model.dto.RouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface RouteMapper {

    RouteDto mapToDto(Route route);

    @Mapping(target = "id", ignore = true)
    Route mapToDomain(RouteDto dto);

}
