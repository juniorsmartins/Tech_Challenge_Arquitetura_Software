package com.techchallenge.devnet.application_business_rules.use_case.admin;

import com.techchallenge.devnet.application_business_rules.ports.entrada.admin.AdminBuscarIndicadoresServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoListarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.objects.Indicador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AdminGetServiceImpl implements AdminBuscarIndicadoresServicePort {

  private final PedidoListarRepositoryPort repositorio;

  public AdminGetServiceImpl(PedidoListarRepositoryPort repositorio) {
    this.repositorio = repositorio;
  }

  @Transactional(readOnly = true)
  @Override
  public Indicador buscarIndicadores() {

    return Optional.of(this.repositorio.listar())
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

