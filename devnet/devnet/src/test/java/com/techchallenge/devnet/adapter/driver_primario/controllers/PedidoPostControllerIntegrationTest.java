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
class PedidoPostControllerIntegrationTest {

//  public static final String END_POINT = "/api/v1/pedidos";
//
//  public static final String UTF8 = "UTF-8";
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @MockBean
//  private IPagamentoOpenFeign pagamentoOpenFeign;
//
//  @Autowired
//  private ClienteRepositoryJpa clienteRepositoryJpa;
//
//  @Autowired
//  private ProdutoRepositoryJpa produtoRepositoryJpa;
//
//  private ClienteModel cliente;
//
//  private ProdutoModel produto;
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
//  }
//
//  @Test
//  @Order(1)
//  @DisplayName("Cadastrar - http 201")
//  void deveRetornarHttp201_quandoCadastrar() throws Exception {
//    Mockito.when(this.pagamentoOpenFeign.cadastrar(Mockito.any()))
//      .thenReturn(ResponseEntity.ok().body(PagamentoDtoResponse.builder().build()));
//
//    var itemPedidoDtoRequest = ItemPedidoDtoRequest.builder()
//      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
//      .quantidade(2)
//      .build();
//
//    var pedidoDtoRequest = PedidoDtoRequest.builder()
//      .cliente(ClienteDtoResumo.builder().id(cliente.getId()).build())
//      .itensPedido(List.of(itemPedidoDtoRequest))
//      .formaPagamento(FormaPagamentoEnum.PIX)
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(pedidoDtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isCreated())
//      .andDo(MockMvcResultHandlers.print());
//  }
//
//  @Test
//  @Order(5)
//  @DisplayName("Cadastrar - http 400 por sem forma de pagamento")
//  void deveRetornarHttp400_quandoCadastrarSemNome() throws Exception {
//    Mockito.when(this.pagamentoOpenFeign.cadastrar(Mockito.any()))
//      .thenReturn(ResponseEntity.ok().body(PagamentoDtoResponse.builder().build()));
//
//    var itemPedidoDtoRequest = ItemPedidoDtoRequest.builder()
//      .produto(ProdutoDtoResumo.builder().id(produto.getId()).build())
//      .quantidade(2)
//      .build();
//
//    var pedidoDtoRequest = PedidoDtoRequest.builder()
//      .itensPedido(List.of(itemPedidoDtoRequest))
//      .cliente(ClienteDtoResumo.builder().id(cliente.getId()).build())
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

