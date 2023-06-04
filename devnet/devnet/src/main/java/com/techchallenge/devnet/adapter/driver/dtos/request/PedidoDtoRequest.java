package com.techchallenge.devnet.adapter.driver.dtos.request;

import com.techchallenge.devnet.adapter.driver.dtos.ClienteDtoResumo;
import com.techchallenge.devnet.core.domain.entities.enums.FormaPagamentoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public final class PedidoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @Valid
  private ClienteDtoResumo cliente;

  @NotNull
  @Size(min = 1)
  @Valid
  private List<ItemPedidoDtoRequest> itensPedido;

  @NotNull
  @Enumerated(EnumType.STRING)
  private FormaPagamentoEnum formaPagamento;
}

