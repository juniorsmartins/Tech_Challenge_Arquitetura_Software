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
class ProdutoPostControllerIntegrationTest {

//  public static final String END_POINT = "/api/v1/produtos";
//
//  public static final String UTF8 = "UTF-8";
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  private ProdutoRepositoryJpa produtoRepositoryJpa;
//
//  @Test
//  @Order(1)
//  @DisplayName("Cadastrar - http 201")
//  void deveRetornarHttp201_quandoCadastrar() throws Exception {
//
//    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isCreated())
//      .andDo(MockMvcResultHandlers.print());
//  }
//
//  @Test
//  @Order(5)
//  @DisplayName("Cadastrar - http 400 por sem nome")
//  void deveRetornarHttp400_quandoCadastrarSemNome() throws Exception {
//
//    var dtoRequest = CriadorDeObjetos.gerarProdutoDtoRequestBuilder()
//      .nome(null)
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isBadRequest())
//      .andDo(MockMvcResultHandlers.print());
//  }
}
