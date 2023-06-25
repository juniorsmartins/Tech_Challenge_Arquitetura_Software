package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.adapter.driver_primario.dtos.PedidoDtoId;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.core.application.ports.entrada.IEmailService;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepository;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.entities.Pedido;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UtilsImpl implements IUtils {

  private static final String EMAIL_ORIGEM = "techchallenge6@gmail.com";

  @Autowired
  private IClienteRepository.GetRepository clienteGetRepository;

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Autowired
  private IEmailService.EnviarService emailEnviarService;

  @Override
  public Pedido confirmarCliente(Pedido pedido) {

    if (ObjectUtils.isNotEmpty(pedido.getCliente()) && ObjectUtils.isNotEmpty(pedido.getCliente().getId())) {
      var idCliente = pedido.getCliente().getId();
      var cliente = this.clienteGetRepository.consultarPorId(idCliente)
        .orElseThrow(() -> new ClienteNaoEncontradoException(idCliente));
      pedido.setCliente(cliente);

    } else if (ObjectUtils.isNotEmpty(pedido.getCliente()) && ObjectUtils.isNotEmpty(pedido.getCliente().getCpf())) {
      var cpfCliente = pedido.getCliente().getCpf();
      var cliente = this.clienteGetRepository.consultarPorCpf(cpfCliente)
        .orElseThrow(() ->
          new ClienteNaoEncontradoException(String.format(MensagemPadrao.CPF_NAO_ENCONTRADO, cpfCliente)));
      pedido.setCliente(cliente);
    }

    return pedido;
  }

  @Override
  public Pedido confirmarProdutos(Pedido pedido) {

    pedido.getItensPedido().forEach(item -> {
      var idProduto = item.getProduto().getId();
      var produto = this.produtoGetRepository.consultarPorId(idProduto)
        .orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto));
      item.setProduto(produto);
    });

    pedido.calcularPrecoTotal();
    return pedido;
  }

  @Override
  public Pedido notificarPedidoRecebido(Pedido pedido) {

    if (ObjectUtils.isNotEmpty(pedido.getCliente())) {

      var cliente = pedido.getCliente();

      var emailDtoRequest = EmailDtoRequest.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido RECEBIDO.")
        .text(cliente.getNome() + ", teu Pedido foi RECEBIDO pela DevNet.")
        .pedido(PedidoDtoId.builder().id(pedido.getId()).build())
        .build();

      this.emailEnviarService.enviar(emailDtoRequest);
    }

    return pedido;
  }

  @Override
  public Pedido notificarPedidoEmPreparacao(Pedido pedido) {

    if (ObjectUtils.isNotEmpty(pedido.getCliente())) {

      var cliente = pedido.getCliente();

      var emailDtoRequest = EmailDtoRequest.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido PAGO em PREPARAÇÃO.")
        .text(cliente.getNome() + ", teu Pedido foi PAGO e está em PREPARAÇÃO.")
        .pedido(PedidoDtoId.builder().id(pedido.getId()).build())
        .build();

      this.emailEnviarService.enviar(emailDtoRequest);
    }

    return pedido;
  }

  @Override
  public Pedido notificarPedidoPronto(Pedido pedido) {

    if (ObjectUtils.isNotEmpty(pedido.getCliente())) {

      var cliente = pedido.getCliente();

      var emailDtoRequest = EmailDtoRequest.builder()
        .ownerRef(cliente.getNome())
        .emailFrom(EMAIL_ORIGEM)
        .emailTo(cliente.getEmail())
        .subject("Notificação - Pedido PRONTO.")
        .text(cliente.getNome() + ", teu Pedido está PRONTO e pode ser retirado.")
        .pedido(PedidoDtoId.builder().id(pedido.getId()).build())
        .build();

      this.emailEnviarService.enviar(emailDtoRequest);
    }

    return pedido;
  }
}

