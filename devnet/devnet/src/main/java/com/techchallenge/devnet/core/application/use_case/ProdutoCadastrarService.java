package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.produto.IProdutoCadastrarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoCadastrarService implements IProdutoCadastrarServicePort {

  @Autowired
  private IProdutoSalvarRepositoryPort produtoSalvarRepository;

  @Override
  public ProdutoModel cadastrar(final ProdutoModel produtoModel) {

    return Optional.of(produtoModel)
      .map(this.produtoSalvarRepository::salvar)
      .orElseThrow();
  }
}

