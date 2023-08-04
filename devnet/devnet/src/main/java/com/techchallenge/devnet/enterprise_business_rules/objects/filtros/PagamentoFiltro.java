package com.techchallenge.devnet.enterprise_business_rules.objects.filtros;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class PagamentoFiltro {

  private String id;

  private StatusPagamentoEnum statusPagamento;

  private String nomeImagemQRCode;
}

