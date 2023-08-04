package com.techchallenge.devnet.interface_adapters.driver_primario.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PedidoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private UUID codigo;

  private StatusPedidoEnum statusPedido;

  private FormaPagamentoEnum formaPagamento;

  private BigDecimal precoTotal;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;

  private List<ItemPedidoDtoResponse> itensPedido;

  private ClienteDtoResponse cliente;

  private PagamentoDtoResponse pagamento;
}

