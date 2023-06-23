package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResumo;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResumo;
import com.techchallenge.devnet.adapter.driver.dtos.requisicao.ItemPedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.entities.Produto;
import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.utils.CriadorDeObjetos;
import com.techchallenge.devnet.utils.Utilitarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest(classes = DevnetApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoPostControllerIntegrationTest {

  public static final String END_POINT = "/api/v1/pedidos";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IPagamentoOpenFeign pagamentoOpenFeign;

  @Autowired
  private ClienteRepositoryJpa clienteRepositoryJpa;

  @Autowired
  private ProdutoRepositoryJpa produtoRepositoryJpa;

  private Cliente cliente;

  private Produto produto;

  @BeforeEach
  void criadorDeCenarios() {

    cliente = CriadorDeObjetos.gerarClienteBuilder()
      .build();
    cliente = this.clienteRepositoryJpa.save(cliente);

    produto = CriadorDeObjetos.gerarProdutoBuilder()
      .build();
    produto = this.produtoRepositoryJpa.save(produto);
  }

  @Test
  @Order(1)
  @DisplayName("Cadastrar - http 201")
  void deveRetornarHttp201_quandoCadastrar() throws Exception {
    Mockito.when(this.pagamentoOpenFeign.cadastrar(Mockito.any()))
      .thenReturn(ResponseEntity.ok().body(PagamentoDtoResponse.builder().build()));

    var itemPedidoDtoRequest = ItemPedidoDtoRequest.builder()
      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
      .quantidade(2)
      .build();

    var pedidoDtoRequest = PedidoDtoRequest.builder()
      .cliente(ClienteDtoResumo.builder().id(cliente.getId()).build())
      .itensPedido(List.of(itemPedidoDtoRequest))
      .formaPagamento(FormaPagamentoEnum.PIX)
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(pedidoDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());
  }

  @Test
  @Order(5)
  @DisplayName("Cadastrar - http 400 por sem forma de pagamento")
  void deveRetornarHttp400_quandoCadastrarSemNome() throws Exception {
    Mockito.when(this.pagamentoOpenFeign.cadastrar(Mockito.any()))
      .thenReturn(ResponseEntity.ok().body(PagamentoDtoResponse.builder().build()));

    var itemPedidoDtoRequest = ItemPedidoDtoRequest.builder()
      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
      .quantidade(2)
      .build();

    var pedidoDtoRequest = PedidoDtoRequest.builder()
      .itensPedido(List.of(itemPedidoDtoRequest))
      .cliente(ClienteDtoResumo.builder().id(cliente.getId()).build())
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(pedidoDtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andDo(MockMvcResultHandlers.print());
  }
}

