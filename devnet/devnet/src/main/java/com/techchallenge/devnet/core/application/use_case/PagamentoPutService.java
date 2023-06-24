package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.ConfirmarPagamentoBloqueadoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoPutService implements IPagamentoService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public PagamentoDtoResponse confirmarPagamento(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(order -> {
        this.verificarSeStatusPedidoPermiteConfirmarPagamento(order);

        order.getPagamento().setStatusPagamento(StatusPagamentoEnum.PAGO);
        order.setStatusPedido(StatusPedidoEnum.PREPARACAO);

        return order.getPagamento();
      })
      .map(pagamento -> this.mapper.converterEntidadeParaDtoResponse(pagamento, PagamentoDtoResponse.class))
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
  }

  private void verificarSeStatusPedidoPermiteConfirmarPagamento(Pedido pedido) {
    if (!pedido.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      throw new ConfirmarPagamentoBloqueadoException(pedido.getId(), pedido.getStatusPedido());
    }
  }
}

