package com.techchallenge.devnet.interface_adapters.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ClienteDao;
import com.techchallenge.devnet.frameworks_and_drivers.db.ClienteRepositoryJpa;
import com.techchallenge.devnet.utils.CriadorDeObjetos;
import com.techchallenge.devnet.utils.Utilitarios;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class ClientePutControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/clientes";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ClienteRepositoryJpa clienteRepositoryJpa;

  private ClienteDao clienteEntity;

  @BeforeEach
  void criadorDeCenarios() {
    clienteEntity = CriadorDeObjetos.gerarClienteDaoBuilder()
      .build();
    clienteEntity = this.clienteRepositoryJpa.save(clienteEntity);
  }

  @AfterEach
  void destruidorDeCenarios() {
    this.clienteRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Atualizar - http 200")
  void deveRetornarHttp200_quandoAtualizar() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + clienteEntity.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  @DisplayName("Atualizar - http 200 e persistir no banco de dados")
  void deveRetornarValoresPersistidos_quandoAtualizar() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .cpf(clienteEntity.getCpf())
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + clienteEntity.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());

    var clienteDoBanco = this.clienteRepositoryJpa.findById(clienteEntity.getId()).get();
    Assertions.assertEquals(clienteEntity.getId(), clienteDoBanco.getId());
    Assertions.assertEquals(dtoRequest.getNome(), clienteDoBanco.getNome());
    Assertions.assertEquals(dtoRequest.getCpf(), clienteDoBanco.getCpf());
    Assertions.assertEquals(dtoRequest.getEmail(), clienteDoBanco.getEmail());
  }

  @Test
  @Order(4)
  @DisplayName("Atualizar - http 404 por id inexistente")
  void deveRetornarHttp404_quandoAtualizarComIdInexistente() throws Exception {

    var idInexistente = Math.round((Math.random() + 1) * 100000);

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + idInexistente)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(5)
  @DisplayName("Atualizar - http 409 por cpf repetido")
  void deveRetornarHttp409_quandoAtualizarComCpfRepetido() throws Exception {

    var clienteEntidade = CriadorDeObjetos.gerarClienteDaoBuilder()
      .build();
    clienteEntidade = this.clienteRepositoryJpa.save(clienteEntidade);

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .cpf(clienteEntity.getCpf())
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + clienteEntidade.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }
}

