package com.techchallenge.devnet.application_business_rules.ports.saida.produto;

import com.techchallenge.devnet.enterprise_business_rules.models.ProdutoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProdutoPesquisarRepositoryPort {

  Page<ProdutoModel> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
}

