package com.techchallenge.devnet.enterprise_business_rules.base.utilitarios;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public final class UtilsClienteImpl implements UtilsCliente {

  private final ClienteConsultarPorIdRepositoryPort repositorioConsultarClientePorId;

  private final ClienteConsultarPorCpfRepositoryPort repositorioConsultarClientePorCpf;

  public UtilsClienteImpl(ClienteConsultarPorIdRepositoryPort repositorioconsultarClientePorId,
                          ClienteConsultarPorCpfRepositoryPort repositorioConsultarClientePorCpf) {
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

