package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.adapter.driven.infra.repositories.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.utils.CriadorDeObjetos;
import com.techchallenge.devnet.utils.Utilitarios;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = DevnetApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientePutControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/clientes";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ClienteRepositoryJpa clienteRepositoryJpa;

  @Test
  @Order(1)
  @DisplayName("Atualizar - http 200")
  void deveRetornarHttp200_quandoAtualizar() throws Exception {

    var entidade = CriadorDeObjetos.gerarClienteBuilder()
      .build();
    entidade = this.clienteRepositoryJpa.save(entidade);

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + entidade.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }
}

