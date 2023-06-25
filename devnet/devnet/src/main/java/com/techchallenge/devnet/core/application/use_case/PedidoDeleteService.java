package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IPedidoService;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.CancelamentoBloqueadoException;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PedidoDeleteService implements IPedidoService.DeletarService {

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void cancelarPorId(final Long id) {

    this.pedidoGetRepository.consultarPorId(id)
      .map(pedido -> {

        if (!pedido.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
          log.info(String.format(MensagemPadrao.CANCELAMENTO_BLOQUEADO, id, pedido.getStatusPedido()));
          throw new CancelamentoBloqueadoException(id, pedido.getStatusPedido());
        }
        pedido.setStatusPedido(StatusPedidoEnum.CANCELADO);
        pedido.getPagamento().setStatusPagamento(StatusPagamentoEnum.CANCELADO);

        return true;
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new PedidoNaoEncontradoException(id);
      });
  }
}

