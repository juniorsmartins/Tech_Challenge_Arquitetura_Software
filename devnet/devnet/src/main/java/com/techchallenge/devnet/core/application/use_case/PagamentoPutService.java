package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.ConfirmarPagamentoBloqueadoException;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PagamentoPutService implements IPagamentoServicePort.PutService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IUtils utils;

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

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
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });
  }

  private PedidoEntity alterarStatusPagamentoParaPagoAndPedidoParaPreparacao(PedidoEntity pedido) {
    if (!pedido.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      log.info(String.format(MensagemPadrao.PAGAMENTO_BLOQUEADO, pedido.getId(), pedido.getStatusPedido()));
      throw new ConfirmarPagamentoBloqueadoException(pedido.getId(), pedido.getStatusPedido());
    }
    pedido.getPagamento().setStatusPagamento(StatusPagamentoEnum.PAGO);
    pedido.setStatusPedido(StatusPedidoEnum.PREPARACAO);
    return pedido;
  }
}

