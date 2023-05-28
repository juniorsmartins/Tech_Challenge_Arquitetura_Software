package com.techchallenge.devnet.core.application.services.regras_negocio;

import com.techchallenge.devnet.core.exceptions.http_409.RegraClienteCpfUnicoException;
import com.techchallenge.devnet.core.domain.Cliente;
import com.techchallenge.devnet.core.application.ports.PoliticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RegraClienteCpfUnico implements RegrasNegocioCliente {

  @Autowired
  private PoliticaRepository.ClienteConsultarRepository consultarRepository;

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

