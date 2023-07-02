package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IAdminService;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.value_objects.Indicador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminGetService implements IAdminService.GetService {

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public Indicador buscarIndicadores() {

    var pedidos = this.pedidoGetRepository.listar();
    var totalPagamentoEmAberto = this.calcularTotalDePagamentoEmAberto(pedidos);
    var totalPagamentoPago = this.calcularTotalDePagamentoPago(pedidos);
    var totalPagamentoCancelado = this.calcularTotalDePagamentoCancelado(pedidos);

    return Indicador.builder()
      .totalPagamentoEmAberto(totalPagamentoEmAberto)
      .totalPagamentoPago(totalPagamentoPago)
      .totalPagamentoCancelado(totalPagamentoCancelado)
      .build();
  }

  private BigDecimal calcularTotalDePagamentoEmAberto(List<PedidoModel> pedidos) {
    return pedidos.stream()
      .filter(pedido -> pedido.getPagamento().getStatusPagamento().equals(StatusPagamentoEnum.ABERTO))
      .map(PedidoModel::getPrecoTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calcularTotalDePagamentoPago(List<PedidoModel> pedidos) {
    return pedidos.stream()
      .filter(pedido -> pedido.getPagamento().getStatusPagamento().equals(StatusPagamentoEnum.PAGO))
      .map(PedidoModel::getPrecoTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calcularTotalDePagamentoCancelado(List<PedidoModel> pedidos) {
    return pedidos.stream()
      .filter(pedido -> pedido.getPagamento().getStatusPagamento().equals(StatusPagamentoEnum.CANCELADO))
      .map(PedidoModel::getPrecoTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}

