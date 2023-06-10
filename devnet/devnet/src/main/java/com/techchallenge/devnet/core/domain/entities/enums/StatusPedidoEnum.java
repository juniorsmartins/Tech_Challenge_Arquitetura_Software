package com.techchallenge.devnet.core.domain.entities.enums;

import lombok.Getter;

@Getter
public enum StatusPedidoEnum {

  RECEBIDO,
  PAGO,
  PREPARACAO,
  PRONTO,
  FINALIZADO,
  CANCELADO;
}

