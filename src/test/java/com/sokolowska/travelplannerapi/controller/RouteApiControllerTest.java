package com.sokolowska.travelplannerapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sokolowska.travelplannerapi.model.dto.PlaceDto;
import com.sokolowska.travelplannerapi.model.dto.RouteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class RouteApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void shouldReturnStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/routes/")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnRouteDtoWhenRouteDtoGiven() throws Exception {

        RouteDto routeDto = RouteDto.builder()
                .destination(PlaceDto.builder().name("Londyn").build())
                .origin(PlaceDto.builder().name("Wroclaw").build())
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/routes/")
                        .with(csrf())
                        .content(asJsonString(routeDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}