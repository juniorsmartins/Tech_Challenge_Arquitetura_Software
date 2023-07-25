package com.techchallenge.devnet.enterprise_business_rules.base.assertions_concern;

import com.techchallenge.devnet.application_business_rules.ports.saida.cliente.ClienteConsultarPorCpfRepositoryPort;
import com.techchallenge.devnet.application_business_rules.exceptions.http_409.RegraClienteCpfUnicoException;
import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import org.springframework.stereotype.Service;

@Service
public final class RegraClienteCpfUnico implements RegrasCliente {

  private final ClienteConsultarPorCpfRepositoryPort repositorio;

  public RegraClienteCpfUnico(ClienteConsultarPorCpfRepositoryPort repositorio) {
    this.repositorio = repositorio;
  }

  @Override
  public void executar(ClienteModel clienteModel) {

    var existeCpfIgual = this.repositorio.consultarPorCpf(clienteModel.getCpf())
      .filter(cliente -> !cliente.getId().equals(clienteModel.getId()))
      .isPresent();

    if (existeCpfIgual) {
      throw new RegraClienteCpfUnicoException(clienteModel.getCpf());
    }
  }
}

