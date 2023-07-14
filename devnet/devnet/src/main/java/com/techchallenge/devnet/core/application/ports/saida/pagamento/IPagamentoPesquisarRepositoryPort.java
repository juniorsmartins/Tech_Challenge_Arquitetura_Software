package com.techchallenge.devnet.core.application.ports.saida.pagamento;

import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.PagamentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPagamentoPesquisarRepositoryPort {

  Page<PagamentoModel> pesquisar(PagamentoFiltro filtro, Pageable paginacao);
}

