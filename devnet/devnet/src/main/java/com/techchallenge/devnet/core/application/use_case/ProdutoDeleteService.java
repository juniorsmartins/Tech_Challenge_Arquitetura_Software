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
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Autowired
  private IProdutoRepository.DeleteRepository produtoDeleteRepository;

  @Transactional(isolation = Isolation.SERIALIZABLE)
  @Override
  public void deletar(final Long id) {

    this.produtoGetRepository.consultarPorId(id)
      .map(produto -> {
        this.produtoDeleteRepository.deletar(produto);
        return true;
      })
      .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
  }
}

