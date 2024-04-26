package com.sokolowska.travelplannerapi.model.mapper;

import com.sokolowska.travelplannerapi.model.Route;
import com.sokolowska.travelplannerapi.model.dto.RouteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PlaceMapper.class)
public interface RouteMapper {

    RouteDto mapToDto(Route route);

    Route mapToDomain(RouteDto dto);

}
