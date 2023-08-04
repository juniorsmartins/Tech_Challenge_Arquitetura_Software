package com.techchallenge.devnet.application_business_rules.use_case.pedido;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.exceptions.http_409.CancelamentoBloqueadoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.PedidoApagarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPagamentoEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PedidoDeleteServiceImpl implements PedidoApagarServicePort {

  private final PedidoConsultarPorIdRepositoryPort repositorioConsultar;

  private final PedidoSalvarRepositoryPort repositorioSalvar;

  public PedidoDeleteServiceImpl(PedidoConsultarPorIdRepositoryPort repositorioConsultar,
                                 PedidoSalvarRepositoryPort repositorioSalvar) {
    this.repositorioConsultar = repositorioConsultar;
    this.repositorioSalvar = repositorioSalvar;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void cancelarPorId(final Long id) {

    this.repositorioConsultar.consultarPorId(id)
      .map(model -> {

        if (!model.getStatusPedido().equals(StatusPedidoEnum.RECEBIDO)) {
          log.info(String.format(MensagemPadrao.CANCELAMENTO_BLOQUEADO, id, model.getStatusPedido()));
          throw new CancelamentoBloqueadoException(id, model.getStatusPedido());
        }
        model.setStatusPedido(StatusPedidoEnum.CANCELADO);
        model.getPagamento().setStatusPagamento(StatusPagamentoEnum.CANCELADO);

        return model;
      })
      .map(this.repositorioSalvar::salvar)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, id));
        throw new PedidoNaoEncontradoException(id);
      });
  }
}

