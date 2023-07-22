package com.techchallenge.devnet.core.application.use_case.pedido;

import com.techchallenge.devnet.core.application.ports.entrada.pedido.IPedidoAtualizarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.AtualizarPedidoBloqueadoException;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PedidoPutService implements IPedidoAtualizarServicePort {

  @Autowired
  private IUtils utils;

  @Autowired
  private IPedidoConsultarPorIdRepositoryPort pedidoGetRepository;

  @Autowired
  private IPedidoSalvarRepositoryPort pedidoPostRepository;

  @Autowired
  private IItemPedidoRepositoryPort.DeleteRepository itemPedidoDeleteRepository;

  @Override
  public PedidoModel atualizar(final Long id, final PedidoModel pedidoModel) {

    return Optional.of(pedidoModel)
      .map(this.utils::confirmarCliente)
      .map(this.utils::confirmarProdutos)
      .map(model -> {
        model.setStatusPedido(StatusPedidoEnum.RECEBIDO);
        model.getItensPedido().forEach(item -> item.setPedido(model));
        model.setId(id);

        this.removerItensPedidoDoPedidoPorId(id);

        return model;
      })
      .map(this.pedidoPostRepository::salvar)
      .orElseThrow();
  }

  private PedidoModel verificarPermissaoParaAtualizar(PedidoModel pedidoModel) {
    if (!pedidoModel.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      log.info(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, pedidoModel.getId(), pedidoModel.getStatusPedido()));
      throw new AtualizarPedidoBloqueadoException(String
        .format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, pedidoModel.getId(), pedidoModel.getStatusPedido()));
    }
    return pedidoModel;
  }

  private void removerItensPedidoDoPedidoPorId(final Long idPedido) {
    this.pedidoGetRepository.consultarPorId(idPedido)
      .map(this::verificarPermissaoParaAtualizar)
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
    this.itemPedidoDeleteRepository.deletarItensDoPedido(idPedido);
  }
}

