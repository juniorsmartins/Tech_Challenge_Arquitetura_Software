package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteDeleteService implements IClienteService.DeletarService {

  @Autowired
  private IClienteRepository.GetRepository clienteGetRepository;

  @Autowired
  private IClienteRepository.DeleteRepository clienteDeleteRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.clienteGetRepository.consultarPorId(id)
      .map(cliente -> {
        this.clienteDeleteRepository.deletar(cliente);
        return true;
      })
      .orElseThrow(() -> new ClienteNaoEncontradoException(id));
  }
}

