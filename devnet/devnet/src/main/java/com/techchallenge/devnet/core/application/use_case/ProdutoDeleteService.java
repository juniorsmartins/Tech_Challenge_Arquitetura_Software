package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoDeleteService implements IProdutoService.DeletarService {

  @Autowired
  private IProdutoRepository.GetRepository getRepository;

  @Autowired
  private IProdutoRepository.DeleteRepository deleteRepository;

  @Override
  public void deletar(final Long id) {

    this.getRepository.consultarPorId(id)
      .map(produto -> {
        this.deleteRepository.deletar(produto);
        return true;
      })
      .orElseThrow();
  }
}

