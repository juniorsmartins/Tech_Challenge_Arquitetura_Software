package com.techchallenge.devnet.core.domain.base.assertions_concern;

import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.RegraClienteCpfUnicoException;
import com.techchallenge.devnet.core.domain.models.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RegraClienteCpfUnico implements RegrasCliente {

  @Autowired
  private IClienteConsultarPorCpfRepositoryPort clienteConsultarPorCpfRepository;

  @Override
  public void executar(ClienteModel clienteModel) {

    var existeCpfIgual = this.clienteConsultarPorCpfRepository.consultarPorCpf(clienteModel.getCpf())
      .filter(cliente -> !cliente.getId().equals(clienteModel.getId()))
      .isPresent();

    if (existeCpfIgual) {
      throw new RegraClienteCpfUnicoException(clienteModel.getCpf());
    }
  }
}

