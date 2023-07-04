package com.techchallenge.devnet.adapter.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = DevnetApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PedidoPutControllerIntegrationTest {

//  public static final String END_POINT = "/api/v1/pedidos";
//
//  public static final String UTF8 = "UTF-8";
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  private ClienteRepositoryJpa clienteRepositoryJpa;
//
//  @Autowired
//  private ProdutoRepositoryJpa produtoRepositoryJpa;
//
//  @Autowired
//  private PedidoRepositoryJpa pedidoRepositoryJpa;
//
//  private ClienteModel cliente;
//
//  private ProdutoModel produto;
//
//  private PedidoEntity pedido;
//
//  @BeforeEach
//  void criadorDeCenarios() {
//
//    cliente = CriadorDeObjetos.gerarClienteBuilder()
//      .build();
//    cliente = this.clienteRepositoryJpa.save(cliente);
//
//    produto = CriadorDeObjetos.gerarProdutoBuilder()
//      .build();
//    produto = this.produtoRepositoryJpa.save(produto);
//
//    var itemPedido = ItemPedidoModel.builder()
//      .produto(produto)
//      .quantidade(2)
//      .build();
//    itemPedido.calcularPrecoParcial();
//
//    pedido = PedidoEntity.builder()
//      .cliente(cliente)
//      .itensPedido(List.of(itemPedido))
//      .formaPagamento(FormaPagamentoEnum.PIX)
//      .statusPedido(StatusPedidoEnum.RECEBIDO)
//      .build();
//    itemPedido.setPedido(pedido);
//    pedido.calcularPrecoTotal();
//    pedido = this.pedidoRepositoryJpa.save(pedido);
//  }
//
//  @AfterEach
//  void destruidorDeCenarios() {
//    this.pedidoRepositoryJpa.deleteAll();
//    this.clienteRepositoryJpa.deleteAll();
//    this.produtoRepositoryJpa.deleteAll();
//  }
//
//  @Test
//  @Order(1)
//  @DisplayName("Atualizar - http 200")
//  void deveRetornarHttp200_quandoCadastrar() throws Exception {
//
//    var produto = CriadorDeObjetos.gerarProdutoBuilder()
//      .build();
//    produto = this.produtoRepositoryJpa.save(produto);
//
//    var itemPedidoDtoRequest1 = ItemPedidoDtoRequest.builder()
//      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
//      .quantidade(1)
//      .build();
//
//    var itemPedidoDtoRequest2 = ItemPedidoDtoRequest.builder()
//      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
//      .quantidade(1)
//      .build();
//
//    var pedidoDtoRequest = PedidoDtoRequest.builder()
//      .cliente(ClienteDtoResumo.builder().id(cliente.getId()).build())
//      .itensPedido(List.of(itemPedidoDtoRequest1, itemPedidoDtoRequest2))
//      .formaPagamento(FormaPagamentoEnum.DINHEIRO)
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + pedido.getId())
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(pedidoDtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isOk())
//      .andDo(MockMvcResultHandlers.print());
//  }
//
//  @Test
//  @Order(5)
//  @DisplayName("Cadastrar - http 400 por sem forma de pagamento")
//  void deveRetornarHttp400_quandoCadastrarSemNome() throws Exception {
//
//    var itemPedidoDtoRequest = ItemPedidoDtoRequest.builder()
//      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
//      .quantidade(2)
//      .build();
//
//    var pedidoDtoRequest = PedidoDtoRequest.builder()
//      .itensPedido(List.of(itemPedidoDtoRequest))
//      .cliente(ClienteDtoResumo.builder().id(cliente.getId()).build())
//      .formaPagamento(null)
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(pedidoDtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isBadRequest())
//      .andDo(MockMvcResultHandlers.print());
//  }
}

