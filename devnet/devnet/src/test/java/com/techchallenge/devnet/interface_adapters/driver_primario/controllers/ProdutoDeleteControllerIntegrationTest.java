package com.techchallenge.devnet.interface_adapters.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.frameworks_and_drivers.db.ProdutoRepositoryJpa;
import com.techchallenge.devnet.utils.CriadorDeObjetos;
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
class ProdutoDeleteControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/produtos";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProdutoRepositoryJpa produtoRepositoryJpa;

  private ProdutoDao produtoEntity;

  @BeforeEach
  void criadorDeCenarios() {
    produtoEntity = CriadorDeObjetos.gerarProdutoDaoBuilder()
      .build();
    produtoEntity = this.produtoRepositoryJpa.save(produtoEntity);
  }

  @AfterEach
  void destruidorDeCenarios() {
    this.produtoRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Deletar - http 204")
  void deveRetornarHttp204_quandoDeletar() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + produtoEntity.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNoContent())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(2)
  @DisplayName("Deletar - http 204 e apagar no banco")
  void deveApagarNoBanco_quandoDeletar() throws Exception {

    this.mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + produtoEntity.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNoContent())
      .andDo(MockMvcResultHandlers.print());

    var existe = this.produtoRepositoryJpa.existsById(produtoEntity.getId());
    Assertions.assertEquals(false, existe);
  }

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

