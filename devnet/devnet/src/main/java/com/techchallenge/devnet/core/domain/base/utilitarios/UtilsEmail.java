package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.application.ports.entrada.email.IEmailEnviarServicePort;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
public final class UtilsEmail implements IUtilsEmail {

  private static final String EMAIL_ORIGEM = "techchallenge6@gmail.com";

  private final IEmailEnviarServicePort serviceEnviarEmail;

  public UtilsEmail(IEmailEnviarServicePort serviceEnviarEmail) {
    this.serviceEnviarEmail = serviceEnviarEmail;
  }

  @Override
  public PedidoModel notificarPedidoRecebido(PedidoModel pedidoModel) {

    if (ObjectUtils.isNotEmpty(pedidoModel.getCliente())) {

      var cliente = pedidoModel.getCliente();

      var emailModel = EmailModel.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido RECEBIDO.")
        .text(cliente.getNome() + ", teu Pedido foi RECEBIDO pela DevNet.")
        .pedido(pedidoModel)
        .build();

      this.serviceEnviarEmail.enviar(emailModel);
    }

    return pedidoModel;
  }

  @Override
  public PedidoModel notificarPedidoEmPreparacao(PedidoModel pedidoModel) {

    if (ObjectUtils.isNotEmpty(pedidoModel.getCliente())) {

      var cliente = pedidoModel.getCliente();

      var emailModel = EmailModel.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido PAGO em PREPARAÇÃO.")
        .text(cliente.getNome() + ", teu Pedido foi PAGO e está em PREPARAÇÃO.")
        .pedido(pedidoModel)
        .build();

      this.serviceEnviarEmail.enviar(emailModel);
    }

    return pedidoModel;
  }

  @Override
  public PedidoModel notificarPedidoPronto(PedidoModel pedidoModel) {

    if (ObjectUtils.isNotEmpty(pedidoModel.getCliente())) {

      var cliente = pedidoModel.getCliente();

      var emailModel = EmailModel.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido PRONTO.")
        .text(cliente.getNome() + ", teu Pedido está PRONTO e pode ser retirado.")
        .pedido(pedidoModel)
        .build();

      this.serviceEnviarEmail.enviar(emailModel);
    }

    return pedidoModel;
  }

  @Override
  public PedidoModel notificarPedidoFinalizado(PedidoModel pedidoModel) {

    if (ObjectUtils.isNotEmpty(pedidoModel.getCliente())) {

      var cliente = pedidoModel.getCliente();

      var emailModel = EmailModel.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido FINALIZADO.")
        .text(cliente.getNome() + ", teu Pedido foi retirado e está FINALIZADO.")
        .pedido(pedidoModel)
        .build();

      this.serviceEnviarEmail.enviar(emailModel);
    }

    return pedidoModel;
  }
}

