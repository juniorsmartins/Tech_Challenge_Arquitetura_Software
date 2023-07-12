package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.application.ports.entrada.IEmailServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UtilsImpl implements IUtils {

  private static final String EMAIL_ORIGEM = "techchallenge6@gmail.com";

  @Autowired
  private IClienteConsultarPorIdRepositoryPort clienteConsultarPorIdRepository;

  @Autowired
  private IClienteConsultarPorCpfRepositoryPort clienteConsultarPorCpfRepository;

  @Autowired
  private IProdutoRepositoryPort.GetRepository produtoGetRepository;

  @Autowired
  private IEmailServicePort.EnviarService emailEnviarService;

  @Override
  public PedidoModel confirmarCliente(PedidoModel pedidoModel) {

    if (ObjectUtils.isNotEmpty(pedidoModel.getCliente()) && ObjectUtils.isNotEmpty(pedidoModel.getCliente().getId())) {
      var idCliente = pedidoModel.getCliente().getId();
      var cliente = this.clienteConsultarPorIdRepository.consultarPorId(idCliente)
        .orElseThrow(() -> new ClienteNaoEncontradoException(idCliente));
      pedidoModel.setCliente(cliente);

    } else if (ObjectUtils.isNotEmpty(pedidoModel.getCliente()) && ObjectUtils.isNotEmpty(pedidoModel.getCliente().getCpf())) {
      var cpfCliente = pedidoModel.getCliente().getCpf();
      var cliente = this.clienteConsultarPorCpfRepository.consultarPorCpf(cpfCliente)
        .orElseThrow(() ->
          new ClienteNaoEncontradoException(String.format(MensagemPadrao.CPF_NAO_ENCONTRADO, cpfCliente)));
      pedidoModel.setCliente(cliente);
    }

    return pedidoModel;
  }

  @Override
  public PedidoModel confirmarProdutos(PedidoModel pedidoModel) {

    pedidoModel.getItensPedido().forEach(item -> {
      var idProduto = item.getProduto().getId();
      var produto = this.produtoGetRepository.consultarPorId(idProduto)
        .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
      item.setProduto(produto);
    });

    pedidoModel.calcularPrecoTotal();
    return pedidoModel;
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

      this.emailEnviarService.enviar(emailModel);
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

      this.emailEnviarService.enviar(emailModel);
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

      this.emailEnviarService.enviar(emailModel);
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

      this.emailEnviarService.enviar(emailModel);
    }

    return pedidoModel;
  }
}

