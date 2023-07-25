package com.techchallenge.devnet.interface_adapters.driver_primario.controllers;

import com.techchallenge.devnet.DevnetApplication;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.PagamentoDao;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.PedidoDao;
import com.techchallenge.devnet.frameworks_and_drivers.db.EmailRepositoryJpa;
import com.techchallenge.devnet.frameworks_and_drivers.db.PagamentoRepositoryJpa;
import com.techchallenge.devnet.frameworks_and_drivers.db.PedidoRepositoryJpa;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.PedidoDtoId;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusEmailEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
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

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest(classes = DevnetApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmailPostControllerAdapterIntegrationTest {

  public static final String END_POINT = "/api/v1/emails";

  public static final String UTF8 = "UTF-8";

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private EmailRepositoryJpa emailRepositoryJpa;

  @Autowired
  private PedidoRepositoryJpa pedidoRepositoryJpa;

  @Autowired
  private PagamentoRepositoryJpa pagamentoRepositoryJpa;

  private PedidoDao pedidoEntity;

  @BeforeEach
  void criadorDeCenarios() {
    pedidoEntity = PedidoDao.builder()
      .codigo(UUID.randomUUID())
      .statusPedido(StatusPedidoEnum.RECEBIDO)
      .formaPagamento(FormaPagamentoEnum.PIX)
      .precoTotal(BigDecimal.valueOf(50))
      .build();
    pedidoEntity = this.pedidoRepositoryJpa.save(pedidoEntity);

    var pagamento = PagamentoDao.builder()
      .statusPagamento(StatusPagamentoEnum.ABERTO)
      .pedido(pedidoEntity)
      .nomeImagemQRCode("Teste")
      .build();
    pagamento = this.pagamentoRepositoryJpa.save(pagamento);
    pedidoEntity.setPagamento(pagamento);
  }

  @AfterEach
  void destruidorDeCenarios() {
    this.emailRepositoryJpa.deleteAll();
    this.pagamentoRepositoryJpa.deleteAll();
    this.pedidoRepositoryJpa.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("Enviar - http 201")
  void deveRetornarHttp201_quandoEnviarEmail() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarEmailDtoRequestBuilder()
      .pedido(PedidoDtoId.builder().id(pedidoEntity.getId()).build())
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
  @Order(2)
  @DisplayName("Cadastrar - http 201 e persistir no banco de dados")
  void deveRetornarValoresPersistidos_quandoEnviarEmail() throws Exception {

    var dtoRequest = CriadorDeObjetos.gerarEmailDtoRequestBuilder()
      .pedido(PedidoDtoId.builder().id(pedidoEntity.getId()).build())
      .build();

    this.mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
        .contentType(MediaType.APPLICATION_JSON)
        .characterEncoding(UTF8)
        .content(Utilitarios.converterObjetoParaJson(dtoRequest))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andDo(MockMvcResultHandlers.print());

    var emailDoBanco = this.emailRepositoryJpa.findByEmailTo(dtoRequest.getEmailTo()).get();
    Assertions.assertNotNull(emailDoBanco.getId());
    Assertions.assertEquals(dtoRequest.getOwnerRef(), emailDoBanco.getOwnerRef());
    Assertions.assertEquals(dtoRequest.getEmailFrom(), emailDoBanco.getEmailFrom());
    Assertions.assertEquals(dtoRequest.getEmailTo(), emailDoBanco.getEmailTo());
    Assertions.assertEquals(dtoRequest.getSubject(), emailDoBanco.getSubject());
    Assertions.assertEquals(dtoRequest.getText(), emailDoBanco.getText());
    Assertions.assertEquals(StatusEmailEnum.SENT, emailDoBanco.getStatusEmail());
  }
}

