package com.techchallenge.devnet.adapter.driver_primario.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.core.domain.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
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

