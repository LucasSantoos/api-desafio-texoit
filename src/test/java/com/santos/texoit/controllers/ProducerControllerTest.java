package com.santos.texoit.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.santos.texoit.ApplicationTests;
import com.santos.texoit.services.enterprise.RestResponseEntityExceptionHandler;

public class ProducerControllerTest extends ApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private ProducerController controller;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(RestResponseEntityExceptionHandler.class).build();
    }

    @Test
    public void getProducer1WithStatusCode200() throws Exception {
        String jsonContent = "{\"id\":1,\"name\":\"Charles B. Wessler\"}";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }

    @Test
    public void shouldReturnNotFoundWhenProducerNotExists() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/500")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("{\"message\":\"Não foi possível localizar o produtor com o código: 500\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getTopAndTailProducersOnAwardsWithStatusCode200() throws Exception {
        final String jsonContent = "{\"min\":[{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991}],\"max\":[{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}]}";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/producers/winners/intervals/top-tail-awards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }
}