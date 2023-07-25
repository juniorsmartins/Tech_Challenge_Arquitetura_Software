package com.techchallenge.devnet.application_business_rules.ports.entrada.pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface IPedidoCadastrarServicePort {

  PedidoModel cadastrar(PedidoModel pedidoModel);
}

