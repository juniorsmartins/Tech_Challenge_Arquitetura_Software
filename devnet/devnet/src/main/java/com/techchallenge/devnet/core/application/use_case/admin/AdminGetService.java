package com.techchallenge.devnet.core.application.use_case.admin;

import com.techchallenge.devnet.core.application.ports.entrada.admin.IAdminBuscarIndicadoresServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.objects.Indicador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AdminGetService implements IAdminBuscarIndicadoresServicePort {

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public Indicador buscarIndicadores() {

    return Optional.of(this.pedidoGetRepository.listar())
      .map(pedidos -> {
        var totalPagamentoEmAberto = this.calcularTotalDePagamentoEmAberto(pedidos);
        var totalPagamentoPago = this.calcularTotalDePagamentoPago(pedidos);
        var totalPagamentoCancelado = this.calcularTotalDePagamentoCancelado(pedidos);

        return Indicador.builder()
          .totalPagamentoEmAberto(totalPagamentoEmAberto)
          .totalPagamentoPago(totalPagamentoPago)
          .totalPagamentoCancelado(totalPagamentoCancelado)
          .build();
      })
      .orElseThrow();
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

