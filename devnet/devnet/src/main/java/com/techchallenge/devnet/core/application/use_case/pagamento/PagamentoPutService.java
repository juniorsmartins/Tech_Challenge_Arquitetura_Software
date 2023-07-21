package com.techchallenge.devnet.core.application.use_case.pagamento;

import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoAtualizarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IGatewayPagamentoPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.ConfirmarPagamentoBloqueadoException;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PagamentoPutService implements IPagamentoAtualizarServicePort {

  @Autowired
  private IUtils utils;

  @Autowired
  private IPedidoRepositoryPort.PostRepository pedidoPostRepository;

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

  @Autowired
  private IGatewayPagamentoPort gatewayPagamento;

  @Transactional
  @Override
  public PagamentoModel verificarStatusNoGateway(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(order -> {

        var foiPago = this.gatewayPagamento.verificarStatusNoGateway(order.getId());
        if (foiPago) {
          order = this.avancarStatusPagamentoPagoAndPedidoPreparacao(order);
          order = this.utils.notificarPedidoEmPreparacao(order);
        }

        return order.getPagamento();
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });
  }

  private PedidoModel avancarStatusPagamentoPagoAndPedidoPreparacao(PedidoModel pedidoModel) {
    if (!pedidoModel.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      log.info(String.format(MensagemPadrao.PAGAMENTO_BLOQUEADO, pedidoModel.getId(), pedidoModel.getStatusPedido()));
      throw new ConfirmarPagamentoBloqueadoException(pedidoModel.getId(), pedidoModel.getStatusPedido());
    }
    pedidoModel.getPagamento().setStatusPagamento(StatusPagamentoEnum.PAGO);
    pedidoModel.setStatusPedido(StatusPedidoEnum.PREPARACAO);
    pedidoModel = this.pedidoPostRepository.salvar(pedidoModel);

    return pedidoModel;
  }
}

