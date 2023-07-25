package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class UtilsPedidoImpl implements UtilsPedido {

  private final IPedidoConsultarPorIdRepositoryPort repositorioConsultarClientePorId;

  public UtilsPedidoImpl(IPedidoConsultarPorIdRepositoryPort repositorioConsultarClientePorId) {
    this.repositorioConsultarClientePorId = repositorioConsultarClientePorId;
  }

  @Override
  public PedidoModel validarPedido(final PedidoModel pedidoModel) {

    var idPedido = pedidoModel.getId();
    return this.repositorioConsultarClientePorId.consultarPorId(idPedido)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });
  }
}

