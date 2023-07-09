package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoPostService implements IProdutoServicePort.PostService {

  @Autowired
  private IProdutoRepositoryPort.PostRepository produtoPostRepository;

  @Override
  public ProdutoModel cadastrar(final ProdutoModel produtoModel) {

    return Optional.of(produtoModel)
      .map(this.produtoPostRepository::salvar)
      .orElseThrow();
  }
}

