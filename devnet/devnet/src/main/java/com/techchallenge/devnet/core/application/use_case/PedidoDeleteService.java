package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IPedidoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.CancelamentoBloqueadoException;
import com.techchallenge.devnet.core.domain.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PedidoDeleteService implements IPedidoServicePort.DeleteService {

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

  @Autowired
  private IPedidoRepositoryPort.PostRepository pedidoPostRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void cancelarPorId(final Long id) {

    this.pedidoGetRepository.consultarPorId(id)
      .map(model -> {

        if (!model.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
          log.info(String.format(MensagemPadrao.CANCELAMENTO_BLOQUEADO, id, model.getStatusPedido()));
          throw new CancelamentoBloqueadoException(id, model.getStatusPedido());
        }
        model.setStatusPedido(StatusPedidoEnum.CANCELADO);
        model.getPagamento().setStatusPagamento(StatusPagamentoEnum.CANCELADO);

        return model;
      })
      .map(this.pedidoPostRepository::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new PedidoNaoEncontradoException(id);
      });
  }
}

