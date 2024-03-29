package com.techchallenge.devnet.interface_adapters.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.interface_adapters.driven_secundario.repositorios.produto.ProdutoRepositoryJpa;
import com.techchallenge.devnet.utils.CriadorDeObjetos;
import com.techchallenge.devnet.utils.Utilitarios;
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
class ProdutoPostControllerIntegrationTest {

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
  @DisplayName("Cadastrar - http 201")
  void deveRetornarHttp201_quandoCadastrar() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
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
  @Order(5)
  @DisplayName("Cadastrar - http 400 por sem nome")
  void deveRetornarHttp400_quandoCadastrarSemNome() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .nome(null)
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
  @Order(6)
  @DisplayName("Cadastrar - http 400 por sem descricao")
  void deveRetornarHttp400_quandoCadastrarSemDescricao() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .descricao(null)
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
  @Order(7)
  @DisplayName("Cadastrar - http 400 por sem categoria")
  void deveRetornarHttp400_quandoCadastrarSemCategoria() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .categoria(null)
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
  @Order(8)
  @DisplayName("Cadastrar - http 400 por sem preco")
  void deveRetornarHttp400_quandoCadastrarSemPreco() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
      .preco(null)
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andDo(MockMvcResultHandlers.print());
  }
}

