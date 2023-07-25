package com.techchallenge.devnet.application_business_rules.use_case.copa;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_409.AtualizarPedidoBloqueadoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.copa.ICopaPedidoFinalizadoServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.copa.ICopaPedidoProntoServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.IPedidoSalvarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsEmail;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CopaPutService implements ICopaPedidoProntoServicePort, ICopaPedidoFinalizadoServicePort {

  private final UtilsEmail utilsEmail;

  private final IPedidoConsultarPorIdRepositoryPort pedidoGetRepository;

  private final IPedidoSalvarRepositoryPort pedidoPostRepository;

  public CopaPutService(UtilsEmail utilsEmail,
                        IPedidoConsultarPorIdRepositoryPort pedidoGetRepository,
                        IPedidoSalvarRepositoryPort pedidoPostRepository) {
    this.utilsEmail = utilsEmail;
    this.pedidoGetRepository = pedidoGetRepository;
    this.pedidoPostRepository = pedidoPostRepository;
  }

  @Override
  public PedidoModel confirmarPedidoPronto(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(this::alterarStatusPedidoParaPronto)
      .map(this.utilsEmail::notificarPedidoPronto)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });
  }

  @Override
  public PedidoModel confirmarPedidoFinalizado(final Long idPedido) {

    return this.pedidoGetRepository.consultarPorId(idPedido)
      .map(this::alterarStatusPedidoParaFinalizado)
      .map(this.utilsEmail::notificarPedidoFinalizado)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });
  }

  private PedidoModel alterarStatusPedidoParaPronto(PedidoModel pedidoModel) {
    if (!pedidoModel.getStatusPedido().equals(StatusPedidoEnum.PREPARACAO)) {
      log.info(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_PRONTO, pedidoModel.getId(), pedidoModel.getStatusPedido()));
      throw new AtualizarPedidoBloqueadoException(String
        .format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_PRONTO, pedidoModel.getId(), pedidoModel.getStatusPedido()));
    }
    pedidoModel.setStatusPedido(StatusPedidoEnum.PRONTO);
    pedidoModel = this.pedidoPostRepository.salvar(pedidoModel);
    return pedidoModel;
  }

  private PedidoModel alterarStatusPedidoParaFinalizado(PedidoModel pedidoModel) {
    if (!pedidoModel.getStatusPedido().equals(StatusPedidoEnum.PRONTO)) {
      log.info(String.format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_FINALIZADO, pedidoModel.getId(), pedidoModel.getStatusPedido()));
      throw new AtualizarPedidoBloqueadoException(String
        .format(MensagemPadrao.PEDIDO_BLOQUEADO_PARA_FINALIZADO, pedidoModel.getId(), pedidoModel.getStatusPedido()));
    }
    pedidoModel.setStatusPedido(StatusPedidoEnum.FINALIZADO);
    pedidoModel = this.pedidoPostRepository.salvar(pedidoModel);
    return pedidoModel;
  }
}

