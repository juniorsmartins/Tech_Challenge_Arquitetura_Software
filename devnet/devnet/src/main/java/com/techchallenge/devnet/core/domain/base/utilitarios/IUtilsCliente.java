package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;

public interface IUtilsCliente {

  PedidoModel checagemDeCliente(PedidoModel pedidoModel);

  ClienteModel validarCliente(ClienteModel clienteModel);
}

