package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IPedidoService;
import com.techchallenge.devnet.core.application.ports.saida.IItemPedidoRepository;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.AtualizarPedidoBloqueadoException;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class PedidoPutService implements IPedidoService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Autowired
  private IItemPedidoRepository.DeleteRepository itemPedidoDeleteRepository;

  @Autowired
  private IUtils utils;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public PedidoDtoResponse atualizar(final Long id, final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Pedido.class))
      .map(this.utils::confirmarCliente)
      .map(this.utils::confirmarProdutos)
      .map(pedido -> {
        pedido.setStatusPedido(StatusPedidoEnum.RECEBIDO);

        var pedidoDoBanco = this.pedidoGetRepository.consultarPorId(id)
          .map(this::verificarPermissaoParaAtualizar)
          .map(order -> {
            order.getItensPedido().forEach(item -> this.itemPedidoDeleteRepository.deletar(item));
            return order;
          })
          .orElseThrow(() -> {
            log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
            throw new PedidoNaoEncontradoException(id);
          });

        BeanUtils.copyProperties(pedido, pedidoDoBanco, "id");
        pedidoDoBanco.getItensPedido().forEach(item -> item.setPedido(pedidoDoBanco));

        return pedidoDoBanco;
      })
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow();
  }

  private Pedido verificarPermissaoParaAtualizar(Pedido pedido) {
    if (!pedido.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      log.info(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, pedido.getId(), pedido.getStatusPedido()));
      throw new AtualizarPedidoBloqueadoException(String
        .format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_ATUALIZAR, pedido.getId(), pedido.getStatusPedido()));
    }
    return pedido;
  }
}

