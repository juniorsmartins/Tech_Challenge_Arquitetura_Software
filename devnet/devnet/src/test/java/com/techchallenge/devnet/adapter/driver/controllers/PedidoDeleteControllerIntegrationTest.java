package com.techchallenge.devnet.adapter.driver.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ClienteRepositoryJpa;
import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.PedidoRepositoryJpa;
import com.techchallenge.devnet.adapter.driven.infra.repositorios.jpa.ProdutoRepositoryJpa;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.entities.ItemPedido;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.Produto;
import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
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

import java.util.List;

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

  @AfterEach
  void destruidorDeCenarios() {
    this.pedidoRepositoryJpa.deleteAll();
    this.clienteRepositoryJpa.deleteAll();
    this.produtoRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Deletar - http 204")
  void deveRetornarHttp204_quandoDeletar() throws Exception {

    var itemPedido = ItemPedido.builder()
      .produto(produto)
      .quantidade(2)
      .build();

    var pedido = Pedido.builder()
      .statusPedido(StatusPedidoEnum.RECEBIDO)
      .formaPagamento(FormaPagamentoEnum.PIX)
      .itensPedido(List.of(itemPedido))
      .cliente(cliente)
      .build();
    itemPedido.calcularPrecoParcial();
    itemPedido.setPedido(pedido);
    pedido.calcularPrecoTotal();
    pedido = this.pedidoRepositoryJpa.save(pedido);

    this.mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT.concat("/") + pedido.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isNoContent())
      .andDo(MockMvcResultHandlers.print());
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

