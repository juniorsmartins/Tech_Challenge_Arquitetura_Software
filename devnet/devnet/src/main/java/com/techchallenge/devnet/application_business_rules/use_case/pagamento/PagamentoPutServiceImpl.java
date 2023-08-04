package com.techchallenge.devnet.application_business_rules.use_case.pagamento;

import com.techchallenge.devnet.application_business_rules.exceptions.http_409.ConfirmarPagamentoBloqueadoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pagamento.PagamentoAtualizarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pagamento.PagamentoGatewayPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoSalvarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsEmail;
import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PagamentoPutServiceImpl implements PagamentoAtualizarServicePort {

  private final UtilsEmail utilsEmail;

  private final PedidoSalvarRepositoryPort pedidoPostRepository;

  private final PedidoConsultarPorIdRepositoryPort pedidoGetRepository;

  private final PagamentoGatewayPort gatewayPagamento;

  public PagamentoPutServiceImpl(UtilsEmail utilsEmail,
                                 PedidoSalvarRepositoryPort pedidoPostRepository,
                                 PedidoConsultarPorIdRepositoryPort pedidoGetRepository,
                                 PagamentoGatewayPort gatewayPagamento) {
    this.utilsEmail = utilsEmail;
    this.pedidoPostRepository = pedidoPostRepository;
    this.pedidoGetRepository = pedidoGetRepository;
    this.gatewayPagamento = gatewayPagamento;
  }

  @Transactional
  @Override
  public PagamentoModel verificarStatusNoGateway(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(order -> {

        var foiPago = this.gatewayPagamento.verificarStatusNoGateway(order.getId());
        if (foiPago) {
          order = this.avancarStatusPagamentoPagoAndPedidoPreparacao(order);
          order = this.utilsEmail.notificarPedidoEmPreparacao(order);
        }

        return order.getPagamento();
      })
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });
  }

  private PedidoModel avancarStatusPagamentoPagoAndPedidoPreparacao(PedidoModel pedidoModel) {
    if (!pedidoModel.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
      log.info(String.format(MensagemPadrao.PAGAMENTO_BLOQUEADO, pedidoModel.getId(), pedidoModel.getStatusPedido()));
      throw new ConfirmarPagamentoBloqueadoException(pedidoModel.getId(), pedidoModel.getStatusPedido());
    }
    pedidoModel.getPagamento().setStatusPagamento(StatusPagamentoEnum.PAGO);
    pedidoModel.setStatusPedido(StatusPedidoEnum.PREPARACAO);
    pedidoModel = this.pedidoPostRepository.salvar(pedidoModel);

    return pedidoModel;
  }
}

