package com.techchallenge.devnet.core.domain.base.utilitarios;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.application.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class UtilsCliente implements IUtilsCliente {

  private final IClienteConsultarPorIdRepositoryPort repositorioConsultarClientePorId;

  private final IClienteConsultarPorCpfRepositoryPort repositorioConsultarClientePorCpf;

  public UtilsCliente(IClienteConsultarPorIdRepositoryPort repositorioconsultarClientePorId,
                      IClienteConsultarPorCpfRepositoryPort repositorioConsultarClientePorCpf) {
    this.repositorioConsultarClientePorId = repositorioconsultarClientePorId;
    this.repositorioConsultarClientePorCpf = repositorioConsultarClientePorCpf;
  }

  @Override
  public PedidoModel checagemDeCliente(PedidoModel pedidoModel) {

    if (ObjectUtils.isNotEmpty(pedidoModel.getCliente()) && ObjectUtils.isNotEmpty(pedidoModel.getCliente().getId())) {
      var idCliente = pedidoModel.getCliente().getId();
      var cliente = this.repositorioConsultarClientePorId.consultarPorId(idCliente)
        .orElseThrow(() -> new ClienteNaoEncontradoException(idCliente));
      pedidoModel.setCliente(cliente);

    } else if (ObjectUtils.isNotEmpty(pedidoModel.getCliente()) && ObjectUtils.isNotEmpty(pedidoModel.getCliente().getCpf())) {
      var cpfCliente = pedidoModel.getCliente().getCpf();
      var cliente = this.repositorioConsultarClientePorCpf.consultarPorCpf(cpfCliente)
        .orElseThrow(() ->
          new ClienteNaoEncontradoException(String.format(MensagemPadrao.CPF_NAO_ENCONTRADO, cpfCliente)));
      pedidoModel.setCliente(cliente);
    }

    return pedidoModel;
  }

  @Override
  public ClienteModel validarCliente(final ClienteModel clienteModel) {

    var idCliente = clienteModel.getId();
    return this.repositorioConsultarClientePorId.consultarPorId(idCliente)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, idCliente));
        throw new ClienteNaoEncontradoException(idCliente);
      });
  }
}

