package com.techchallenge.devnet.adapter.driver.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResumo;
import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class PedidoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private ClienteDtoResumo cliente;

  private List<ItemPedidoDtoResponse> itensPedido;

  private FormaPagamentoEnum formaPagamento;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

