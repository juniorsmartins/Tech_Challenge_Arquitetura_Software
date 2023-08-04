package com.techchallenge.devnet.enterprise_business_rules.models;

import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class PagamentoModel implements Serializable {

  public static final long serialVersionUID = 1L;

  private Long id;

  private StatusPagamentoEnum statusPagamento = StatusPagamentoEnum.ABERTO;

  private PedidoModel pedido;

  private String nomeImagemQRCode;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;
}

