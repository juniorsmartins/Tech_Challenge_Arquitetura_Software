package com.techchallenge.devnet.core.application.ports.saida.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

import java.util.List;

public interface IPedidoListarRepositoryPort {

  List<PedidoModel> listar();
}

