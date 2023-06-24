package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.resposta.PagamentoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IPagamentoRepository;
import com.techchallenge.devnet.core.application.ports.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_400.IdsIncompativeis;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PagamentoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.Pagamento;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.core.domain.entities.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoPutService implements IPagamentoService.AtualizarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Autowired
  private IPagamentoRepository.GetRepository pagamentoGetRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public PagamentoDtoResponse confirmarPagamentoFeito(final Long idPedido, final Long idPagamento) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(order -> {

        var pagamento = this.pagamentoGetRepository.consultarPorId(idPagamento)
          .map(pgto -> {
            this.verificarCompatibilidadeDeIds(order, pgto);
            pgto.setStatusPagamento(StatusPagamentoEnum.PAGO);
            return pgto;
          })
          .orElseThrow(() -> new PagamentoNaoEncontradoException(idPagamento));

        order.setStatusPedido(StatusPedidoEnum.PREPARACAO);

        return pagamento;
      })
      .map(pagamento -> this.mapper.converterEntidadeParaDtoResponse(pagamento, PagamentoDtoResponse.class))
      .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));
  }

  private void verificarCompatibilidadeDeIds(final Pedido pedido, final Pagamento pagamento) {
    if (!pedido.getPagamento().getId().equals(pagamento.getId())) {
      throw new IdsIncompativeis(pedido.getId(), pagamento.getId());
    }
  }
}

