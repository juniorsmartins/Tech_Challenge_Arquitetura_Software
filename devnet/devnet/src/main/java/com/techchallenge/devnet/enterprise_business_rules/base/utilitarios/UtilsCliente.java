package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

public interface UtilsCliente {

  PedidoModel checagemDeCliente(PedidoModel pedidoModel);

  ClienteModel validarCliente(ClienteModel clienteModel);
}

