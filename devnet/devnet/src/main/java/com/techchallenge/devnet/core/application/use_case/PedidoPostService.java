package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.base.utils.IUtils;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import com.techchallenge.devnet.core.domain.value_objects.CobrancaPagamentoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostService implements IPedidoService.CadastrarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IUtils utils;

  @Autowired
  private IPagamentoService.PagamentoPostService pagamentoPostService;

  @Autowired
  private IPedidoRepository.PostRepository pedidoPostRepository;

  @Transactional
  @Override
  public CobrancaPagamentoDto cadastrar(final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Pedido.class))
      .map(this.utils::confirmarCliente)
      .map(this.utils::confirmarProdutos)
      .map(this::organizarPedidoParaRegistrar)
      .map(this.pedidoPostRepository::salvar)
      .map(this.pagamentoPostService::iniciarCobrancaDePagamento)
      .orElseThrow();
  }

  private Pedido organizarPedidoParaRegistrar(Pedido pedido) {

    pedido.setStatusPedido(StatusPedidoEnum.RECEBIDO);
    pedido.getItensPedido().forEach(item -> item.setPedido(pedido));
    pedido.calcularPrecoTotal();

    return pedido;
  }
}

