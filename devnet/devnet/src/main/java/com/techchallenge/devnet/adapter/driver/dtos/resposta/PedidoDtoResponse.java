package com.techchallenge.devnet.adapter.driver.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PedidoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private ClienteDtoResponse cliente;

  private StatusPedidoEnum statusPedido;

  private List<ItemPedidoDtoResponse> itensPedido;

  private FormaPagamentoEnum formaPagamento;

  private BigDecimal precoTotal;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;

  private PagamentoDtoResponse pagamento;
}

