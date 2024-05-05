package com.sokolowska.travelplannerapi.model.mapper;

import com.sokolowska.travelplannerapi.model.Flight;
import com.sokolowska.travelplannerapi.model.Route;
import com.sokolowska.travelplannerapi.model.dto.FlightDto;
import com.sokolowska.travelplannerapi.model.dto.RouteDto;
import com.sokolowska.travelplannerapi.webclient.tequilaapi.dto.TequilaFlightDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {

    FlightDto toDto(Flight route);

    Flight toEty(FlightDto dto);

    List<FlightDto> toDtoList(List<Flight> flights);

    @Mapping(source = "deepLink", target = "link")
    @Mapping(source = "localArrival", target = "arrival", qualifiedByName = "parseDateTime")
    @Mapping(source = "localDeparture", target = "departure", qualifiedByName = "parseDateTime")
    FlightDto toDto(TequilaFlightDataDto tequilaFlightDataDto);

    @Named("parseDateTime")
    default String parseDateTime(String dateTime) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime);
        return offsetDateTime.toLocalDateTime().toString();
    }
}
