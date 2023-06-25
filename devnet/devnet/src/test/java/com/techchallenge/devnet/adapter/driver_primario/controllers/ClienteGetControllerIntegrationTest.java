package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = DevnetApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteGetControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/clientes";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  @DisplayName("Pesquisar - http 200")
  void deveRetornarHttp200_quandoPesquisarTodos() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }
}

