package com.techchallenge.devnet.core.application.ports.saida.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

import java.util.List;

public interface IPedidoBuscarPorIdClienteRepositoryPort {

  List<PedidoModel> buscarPorIdDeCliente(Long idCliente);
}

