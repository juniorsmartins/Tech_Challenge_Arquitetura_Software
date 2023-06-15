package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.PagamentoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.request.PedidoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.PedidoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPagamentoOpenFeign;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.base.utils.IUtils;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import org.apache.commons.lang3.ObjectUtils;
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
  private IPagamentoOpenFeign pagamentoOpenFeign;

  @Autowired
  private IUtils utils;

  @Transactional
  @Override
  public PedidoDtoResponse cadastrar(final PedidoDtoRequest dtoRequest) {

    var dtoResponse = Optional.of(dtoRequest)
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

    this.abrirSolicitacaoDePagamento(dtoResponse);


    return dtoResponse;
  }

  private void abrirSolicitacaoDePagamento(PedidoDtoResponse pedidoDtoResponse) {


    var solicitacaoDePagamento = PagamentoDtoRequest.builder()
      .formaPagamento(pedidoDtoResponse.getFormaPagamento())
      .precoTotal(pedidoDtoResponse.getPrecoTotal())
      .build();

    var response = this.pagamentoOpenFeign.cadastrar(solicitacaoDePagamento);
    if (ObjectUtils.isNotEmpty(response)) {
      System.out.println("\n\n---------- Concluída comunicação de pagamento ----------\n\n");
    }

  }
}

