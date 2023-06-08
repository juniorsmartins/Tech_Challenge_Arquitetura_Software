package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.IItemPedidoRepository;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.base.utils.IUtils;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPutService implements IPedidoService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository repository;

  @Autowired
  private IClienteRepository.GetRepository clienteRepository;

  @Autowired
  private IProdutoRepository.GetRepository produtoRepository;

  @Autowired
  private IItemPedidoRepository.DeleteRepository itemPedidoRepository;

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
        pedido.setStatusPedido(StatusPedidoEnum.CRIADO);

        var pedidoDoBanco = this.repository.consultarPorId(id)
          .map(order -> {
            order.getItensPedido().forEach(item -> this.itemPedidoRepository.deletar(item));
            return order;
          })
          .orElseThrow(() -> new PedidoNaoEncontradoException(id));

        BeanUtils.copyProperties(pedido, pedidoDoBanco, "id");
        pedidoDoBanco.getItensPedido().forEach(item -> item.setPedido(pedidoDoBanco));

        return pedidoDoBanco;
      })
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow();
  }
}

