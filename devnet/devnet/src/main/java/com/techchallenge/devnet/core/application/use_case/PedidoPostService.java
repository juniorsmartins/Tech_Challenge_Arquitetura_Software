package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.base.utils.IUtils;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostService implements IPedidoService.CadastrarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.PostRepository pedidoPostRepository;

  @Autowired
  private IClienteRepository.GetRepository clienteGetRepository;

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Autowired
  private IUtils utils;

  @Transactional
  @Override
  public PedidoDtoResponse cadastrar(final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Pedido.class))
      .map(this.utils::confirmarCliente)
      .map(this.utils::confirmarProdutos)
      .map(pedido -> {
        pedido.setStatusPedido(StatusPedidoEnum.RECEBIDO);
        pedido.getItensPedido().forEach(item -> item.setPedido(pedido));
        pedido.calcularPrecoTotal();
        return pedido;
      })
      .map(this.pedidoPostRepository::salvar)
      .map(pedido -> this.mapper.converterEntidadeParaDtoResponse(pedido, PedidoDtoResponse.class))
      .orElseThrow();
  }
}

