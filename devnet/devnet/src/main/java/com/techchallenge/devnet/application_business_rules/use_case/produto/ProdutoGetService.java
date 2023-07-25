package com.techchallenge.devnet.application_business_rules.use_case.produto;

import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.IProdutoPesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.IProdutoPesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ProdutoFiltro;
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

