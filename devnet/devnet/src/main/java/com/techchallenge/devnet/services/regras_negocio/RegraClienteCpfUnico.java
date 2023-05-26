package com.techchallenge.devnet.services.regras_negocio;

import com.techchallenge.devnet.exceptions.http_409.RegraClienteCpfUnicoException;
import com.techchallenge.devnet.models.Cliente;
import com.techchallenge.devnet.repositories.PoliticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class RegraClienteCpfUnico implements RegrasNegocioCliente<Cliente> {

  @Autowired
  private PoliticaRepository.ClienteConsultarRepository consultarRepository;

  @Override
  public void executarRegrasDeNegocio(Cliente entidade) {

    var existeCpfIgual = this.consultarRepository.consultarPorCodigo(entidade.getCodigo())
      .filter(cliente -> !entidade.getId().equals(cliente.getId()))
      .isPresent();

    if (existeCpfIgual) {
      throw new RegraClienteCpfUnicoException(entidade.getCpf());
    }
  }
}

