package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoService;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.ConfirmarPagamentoBloqueadoException;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
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
  private IUtils utils;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public PagamentoDtoResponse confirmarPagamento(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(order -> {
        order = this.alterarStatusPagamentoParaPagoAndPedidoParaPreparacao(order);
        order = this.utils.notificarPedidoEmPreparacao(order);
        return order.getPagamento();
      })
      .map(pagamento -> this.mapper.converterEntidadeParaDtoResponse(pagamento, PagamentoDtoResponse.class))
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
  }

  private Pedido alterarStatusPagamentoParaPagoAndPedidoParaPreparacao(Pedido pedido) {
    if (!pedido.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      throw new ConfirmarPagamentoBloqueadoException(pedido.getId(), pedido.getStatusPedido());
    }
    pedido.getPagamento().setStatusPagamento(StatusPagamentoEnum.PAGO);
    pedido.setStatusPedido(StatusPedidoEnum.PREPARACAO);
    return pedido;
  }
}

