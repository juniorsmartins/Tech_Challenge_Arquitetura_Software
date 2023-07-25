package com.techchallenge.devnet.application_business_rules.ports.saida.pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface IPedidoSalvarRepositoryPort {

  PedidoModel salvar(PedidoModel pedidoModel);
}

