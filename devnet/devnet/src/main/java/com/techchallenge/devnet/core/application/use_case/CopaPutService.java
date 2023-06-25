package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.ICopaService;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.AtualizarPedidoBloqueadoException;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CopaPutService implements ICopaService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IUtils utils;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Transactional
  @Override
  public PedidoDtoResponse confirmarPedidoPronto(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(this::alterarStatusPedidoParaPronto)
      .map(this.utils::notificarPedidoPronto)
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
  }

  private Pedido alterarStatusPedidoParaPronto(Pedido pedido) {
    if (!pedido.getStatusPedido().equals(StatusPedidoEnum.PREPARACAO)) {
      throw new AtualizarPedidoBloqueadoException(String
        .format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_PRONTO, pedido.getId(), pedido.getStatusPedido()));
    }
    pedido.setStatusPedido(StatusPedidoEnum.PRONTO);
    return pedido;
  }
}

