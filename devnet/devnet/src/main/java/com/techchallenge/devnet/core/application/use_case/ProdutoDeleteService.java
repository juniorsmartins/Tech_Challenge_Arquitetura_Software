package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoDeleteService implements IProdutoService.DeletarService {

  @Autowired
  private IProdutoRepository.GetRepository getRepository;

  @Autowired
  private IProdutoRepository.DeleteRepository deleteRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.getRepository.consultarPorId(id)
      .map(produto -> {
        this.deleteRepository.deletar(produto);
        return true;
      })
      .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
  }
}

