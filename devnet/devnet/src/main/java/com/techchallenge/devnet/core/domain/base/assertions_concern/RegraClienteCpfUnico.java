package com.techchallenge.devnet.core.domain.base.assertions_concern;

import com.techchallenge.devnet.core.domain.base.exceptions.http_409.RegraClienteCpfUnicoException;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RegraClienteCpfUnico implements RegrasNegocioCliente {

  @Autowired
  private IClienteRepository.GetRepository consultarRepository;

  @Override
  public void executarRegrasDeNegocio(Cliente entidade) {

    var existeCpfIgual = this.consultarRepository.consultarPorCpf(entidade.getCpf())
      .filter(cliente -> !cliente.getId().equals(entidade.getId()))
      .isPresent();

    if (existeCpfIgual) {
      throw new RegraClienteCpfUnicoException(entidade.getCpf());
    }
  }
}

