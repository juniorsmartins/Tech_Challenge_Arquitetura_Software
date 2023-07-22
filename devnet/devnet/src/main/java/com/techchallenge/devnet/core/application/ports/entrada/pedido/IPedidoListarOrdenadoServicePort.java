package com.techchallenge.devnet.core.application.ports.entrada.pedido;

import com.techchallenge.devnet.core.domain.models.PedidoModel;

import java.util.List;

public interface IPedidoListarOrdenadoServicePort {

  List<PedidoModel> listarOrdenadoPorStatusAndDataHoraCadastro();
}

