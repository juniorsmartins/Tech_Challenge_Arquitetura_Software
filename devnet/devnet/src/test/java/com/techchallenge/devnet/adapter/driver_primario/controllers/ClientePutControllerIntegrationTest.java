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
class ClientePutControllerIntegrationTest {

//  public static final String END_POINT = "/api/v1/clientes";
//
//  public static final String UTF8 = "UTF-8";
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Autowired
//  private ClienteRepositoryJpa clienteRepositoryJpa;
//
//  @Test
//  @Order(1)
//  @DisplayName("Atualizar - http 200")
//  void deveRetornarHttp200_quandoAtualizar() throws Exception {
//
//    var entidade = CriadorDeObjetos.gerarClienteBuilder()
//      .build();
//    entidade = this.clienteRepositoryJpa.save(entidade);
//
//    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + entidade.getId())
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isOk())
//      .andDo(MockMvcResultHandlers.print());
//  }
//
//  @Test
//  @Order(5)
//  @DisplayName("Atualizar - http 404 por id inexistente")
//  void deveRetornarHttp404_quandoAtualizarComIdInexistente() throws Exception {
//
//    var idInexistente = Math.round((Math.random() + 1) * 100000);
//
//    var dtoRequest = CriadorDeObjetos.gerarClienteDtoRequestBuilder()
//      .build();
//
//    this.mockMvc.perform(MockMvcRequestBuilders.put(END_POINT.concat("/") + idInexistente)
//        .contentType(MediaType.APPLICATION_JSON)
//        .characterEncoding(UTF8)
//        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
//        .accept(MediaType.APPLICATION_JSON))
//      .andExpect(MockMvcResultMatchers.status().isNotFound())
//      .andDo(MockMvcResultHandlers.print());
//  }
}

