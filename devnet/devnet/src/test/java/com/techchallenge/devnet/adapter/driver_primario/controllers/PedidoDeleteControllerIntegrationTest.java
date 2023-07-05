package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ClienteEntity;
import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.adapter.driven_secundario.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.utils.CriadorDeObjetos;
import org.junit.jupiter.api.AfterEach;
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
class PedidoDeleteControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/pedidos";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PedidoRepositoryJpa pedidoRepositoryJpa;

  @Autowired
  private ClienteRepositoryJpa clienteRepositoryJpa;

  @Autowired
  private ProdutoRepositoryJpa produtoRepositoryJpa;

  private ClienteEntity clienteEntity;

  private ProdutoEntity produtoEntity1;

  private ProdutoEntity produtoEntity2;

  @BeforeEach
  void criadorDeCenarios() {

    clienteEntity = CriadorDeObjetos.gerarClienteEntityBuilder()
      .build();
    clienteEntity = this.clienteRepositoryJpa.save(clienteEntity);

    produtoEntity1 = CriadorDeObjetos.gerarProdutoEntityBuilder()
      .build();
    produtoEntity1 = this.produtoRepositoryJpa.save(produtoEntity1);

    produtoEntity2 = CriadorDeObjetos.gerarProdutoEntityBuilder()
      .build();
    produtoEntity2 = this.produtoRepositoryJpa.save(produtoEntity2);
  }

  @AfterEach
  void destruidorDeCenarios() {
    this.pedidoRepositoryJpa.deleteAll();
    this.clienteRepositoryJpa.deleteAll();
    this.produtoRepositoryJpa.deleteAll();
  }

//  @Test
//  @Order(1)
//  @DisplayName("Deletar - http 204")
//  void deveRetornarHttp204_quandoDeletar() throws Exception {
//
//    this.mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pedidoEntity.getId())
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isNoContent())
//      .andDo(MockMvcResultHandlers.print());
//  }

  @Test
  @Order(5)
  @DisplayName("Deletar - http 404 por id inexistente")
  void deveRetornarHttp404_quandoDeletarComIdInexistente() throws Exception {

    var idInexistente = Math.round((Math.random() + 1) * 100000);

    this.mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + idInexistente)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andDo(MockMvcResultHandlers.print());
  }
}

