package com.techchallenge.devnet.interface_adapters.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.produto.ProdutoRepositoryJpa;
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
class ProdutoPutControllerIntegrationTest {

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
  @DisplayName("Atualizar - http 200")
  void deveRetornarHttp200_quandoAtualizar() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + produtoEntity.getId())
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

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + produtoEntity.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());

    var produtoDoBanco = this.produtoRepositoryJpa.findById(produtoEntity.getId()).get();
    Assertions.assertEquals(produtoEntity.getId(), produtoDoBanco.getId());
    Assertions.assertEquals(dtoRequest.getNome(), produtoDoBanco.getNome());
    Assertions.assertEquals(dtoRequest.getDescricao(), produtoDoBanco.getDescricao());
    Assertions.assertEquals(dtoRequest.getCategoria(), produtoDoBanco.getCategoria());
    Assertions.assertEquals(dtoRequest.getPreco(), produtoDoBanco.getPreco());
  }

  @Test
  @Order(5)
  @DisplayName("Atualizar - http 404 por id inexistente")
  void deveRetornarHttp404_quandoAtualizarComIdInexistente() throws Exception {

    var idInexistente = Math.round((Math.random() + 1) * 100000);

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + idInexistente)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNotFound())
      .andDo(MockMvcResultHandlers.print());
  }
}

