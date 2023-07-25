package com.techchallenge.devnet.application_business_rules.use_case.produto;

import com.techchallenge.devnet.application_business_rules.ports.entrada.produto.ProdutoPesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.ProdutoPesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoGetServiceImpl implements ProdutoPesquisarServicePort {

  private final ProdutoPesquisarRepositoryPort repositorioPesquisarProduto;

  public ProdutoGetServiceImpl(ProdutoPesquisarRepositoryPort repositorioPesquisarProduto) {
    this.repositorioPesquisarProduto = repositorioPesquisarProduto;
  }

  @Override
  public Page<ProdutoModel> pesquisar(final ProdutoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repositorioPesquisarProduto.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

