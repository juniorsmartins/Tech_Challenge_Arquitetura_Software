package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClienteDeleteService implements IClienteService.DeletarService {

  @Autowired
  private IClienteRepository.GetRepository getRepository;

  @Autowired
  private IClienteRepository.DeleteRepository deleteRepository;

  @Override
  public void deletar(final Long id) {

    this.getRepository.consultarPorId(id)
      .map(cliente -> {
        this.deleteRepository.deletar(cliente);
        return true;
      })
      .orElseThrow();
  }
}
