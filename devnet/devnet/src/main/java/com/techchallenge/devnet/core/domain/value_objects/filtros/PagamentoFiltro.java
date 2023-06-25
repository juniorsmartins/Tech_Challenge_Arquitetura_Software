package com.techchallenge.devnet.core.domain.value_objects.filtros;

import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PagamentoFiltro {

  private String id;

  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;
}

