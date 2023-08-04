package com.techchallenge.devnet.application_business_rules.ports.saida.pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

import java.util.List;

public interface PedidoBuscarPorIdClienteRepositoryPort {

  List<PedidoModel> buscarPorIdDeCliente(Long idCliente);
}

