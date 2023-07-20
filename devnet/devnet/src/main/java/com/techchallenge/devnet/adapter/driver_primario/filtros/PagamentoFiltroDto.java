package com.techchallenge.devnet.adapter.driver_primario.filtros;

import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PagamentoFiltroDto {

  private String id;

  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;
}

