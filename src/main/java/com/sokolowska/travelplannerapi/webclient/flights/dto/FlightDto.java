package com.sokolowska.travelplannerapi.tequila;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDto {

    private String cityFrom;
    private String cityTo;
    private int price;
    private String link;
    private String arrival;
    private String departure;

    @JsonProperty("data")
    private void unpackNested(List<Map<String, Object>> data) {
        if (!data.isEmpty()) {
            this.cityFrom = (String) data.get(0).get("cityFrom");
            this.cityTo = (String) data.get(0).get("cityTo");
            this.price = (int) data.get(0).get("price");
            this.link = (String) data.get(0).get("deep_link");
            this.arrival = (String) data.get(0).get("local_arrival");
            this.departure = (String) data.get(0).get("local_departure");
        }
    }
}
