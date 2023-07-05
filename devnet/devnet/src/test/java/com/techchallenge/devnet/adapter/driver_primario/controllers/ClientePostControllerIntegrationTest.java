package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ClienteEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ClienteRepositoryJpa;
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
class ClientePostControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/clientes";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ClienteRepositoryJpa clienteRepositoryJpa;

  private ClienteEntity clienteEntity;

  @BeforeEach
  void criadorDeCenarios() {
    clienteEntity = CriadorDeObjetos.gerarClienteEntityBuilder()
      .build();
    clienteEntity = this.clienteRepositoryJpa.save(clienteEntity);
  }

  @AfterEach
  void destruidorDeCenarios() {
    this.clienteRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Cadastrar - http 201")
  void deveRetornarHttp201_quandoCadastrar() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  @DisplayName("Cadastrar - http 201 e persistir no banco de dados")
  void deveRetornarValoresPersistidos_quandoCadastrar() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());

    var clienteDoBanco = this.clienteRepositoryJpa.findByCpf(dtoRequest.getCpf()).get();
    Assertions.assertEquals(clienteDoBanco.getId(), clienteDoBanco.getId());
    Assertions.assertEquals(dtoRequest.getNome(), clienteDoBanco.getNome());
    Assertions.assertEquals(dtoRequest.getCpf(), clienteDoBanco.getCpf());
    Assertions.assertEquals(dtoRequest.getEmail(), clienteDoBanco.getEmail());
  }

  @Test
  @Order(3)
  @DisplayName("Cadastrar - http 400 por CPF falso")
  void deveRetornarHttp400_quandoCadastrarComCpfFalso() throws Exception {

    var cpfFalso = "11122233380";
    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .cpf(cpfFalso)
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(4)
  @DisplayName("Cadastrar - http 409 por CPF repetido")
  void deveRetornarHttp409_quandoCadastrarComCpfRepetido() throws Exception {

    var cpfRepetido = clienteEntity.getCpf();
    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
      .cpf(cpfRepetido)
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isConflict())
      .andDo(MockMvcResultHandlers.print());
  }
}


