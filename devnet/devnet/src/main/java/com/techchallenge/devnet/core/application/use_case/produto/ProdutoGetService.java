package com.techchallenge.devnet.core.application.use_case.produto;

import com.techchallenge.devnet.core.application.ports.entrada.produto.IProdutoPesquisarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoPesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoGetService implements IProdutoPesquisarServicePort {

  private final IProdutoPesquisarRepositoryPort repositorioPesquisarProduto;

  public ProdutoGetService(IProdutoPesquisarRepositoryPort repositorioPesquisarProduto) {
    this.repositorioPesquisarProduto = repositorioPesquisarProduto;
  }

  @Override
  public Page<ProdutoModel> pesquisar(final ProdutoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repositorioPesquisarProduto.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

