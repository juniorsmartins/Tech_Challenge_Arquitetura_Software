package com.techchallenge.devnet.application_business_rules.ports.entrada.pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;

import java.util.List;

public interface PedidoListarOrdenadoServicePort {

  List<PedidoModel> listarOrdenadoPorStatusAndDataHoraCadastro();
}

