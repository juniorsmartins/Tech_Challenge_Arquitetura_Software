package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IPagamentoServicePort;
import com.techchallenge.devnet.core.application.ports.entrada.IPedidoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtils;
import com.techchallenge.devnet.adapter.driven_secundario.entities.PedidoEntity;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PedidoPostService implements IPedidoServicePort.PostService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IUtils utils;

  @Autowired
  private IPagamentoServicePort.PostService pagamentoPostService;

  @Autowired
  private IPedidoRepositoryPort.PostRepository pedidoPostRepository;

  @Transactional
  @Override
  public PedidoDtoResponse cadastrar(final PedidoDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, PedidoEntity.class))
      .map(this.utils::confirmarCliente)
      .map(this.utils::confirmarProdutos)
      .map(this::organizarPedidoParaRegistrar)
      .map(this.pedidoPostRepository::salvar)
      .map(this.pagamentoPostService::iniciarCobrancaDePagamento)
      .map(this.utils::notificarPedidoRecebido)
      .map(entidade -> this.mapper.converterEntidadeParaDtoResponse(entidade, PedidoDtoResponse.class))
      .orElseThrow();
  }

  private PedidoEntity organizarPedidoParaRegistrar(PedidoEntity pedido) {

    pedido.setStatusPedido(StatusPedidoEnum.RECEBIDO);
    pedido.getItensPedido().forEach(item -> item.setPedido(pedido));
    pedido.calcularPrecoTotal();

    return pedido;
  }
}

