package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.IndicadoresDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IAdminService;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
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
  public IndicadoresDtoResponse buscarIndicadores() {

    var pedidos = this.pedidoGetRepository.listar();
    var totalPagamentoEmAberto = this.calcularTotalDePagamentoEmAberto(pedidos);
    var totalPagamentoPago = this.calcularTotalDePagamentoPago(pedidos);
    var totalPagamentoCancelado = this.calcularTotalDePagamentoCancelado(pedidos);

    return IndicadoresDtoResponse.builder()
      .totalPagamentoEmAberto(totalPagamentoEmAberto)
      .totalPagamentoPago(totalPagamentoPago)
      .totalPagamentoCancelado(totalPagamentoCancelado)
      .build();
  }

  private BigDecimal calcularTotalDePagamentoEmAberto(List<PedidoEntity> pedidos) {
    return pedidos.stream()
      .filter(pedido -> pedido.getPagamento().getStatusPagamento().equals(StatusPagamentoEnum.ABERTO))
      .map(PedidoEntity::getPrecoTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calcularTotalDePagamentoPago(List<PedidoEntity> pedidos) {
    return pedidos.stream()
      .filter(pedido -> pedido.getPagamento().getStatusPagamento().equals(StatusPagamentoEnum.PAGO))
      .map(PedidoEntity::getPrecoTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calcularTotalDePagamentoCancelado(List<PedidoEntity> pedidos) {
    return pedidos.stream()
      .filter(pedido -> pedido.getPagamento().getStatusPagamento().equals(StatusPagamentoEnum.CANCELADO))
      .map(PedidoEntity::getPrecoTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}

