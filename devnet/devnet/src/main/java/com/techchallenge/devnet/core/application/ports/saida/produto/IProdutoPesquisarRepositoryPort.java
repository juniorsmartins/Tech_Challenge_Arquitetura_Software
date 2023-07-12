package com.techchallenge.devnet.core.application.ports.saida.produto;

import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ProdutoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProdutoPesquisarRepositoryPort {

  Page<ProdutoModel> pesquisar(ProdutoFiltro filtro, Pageable paginacao);
}

