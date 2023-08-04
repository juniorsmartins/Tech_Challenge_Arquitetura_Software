package com.techchallenge.devnet.application_business_rules.ports.saida.pagamento;

import com.techchallenge.devnet.enterprise_business_rules.models.PagamentoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PagamentoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PagamentoPesquisarRepositoryPort {

  Page<PagamentoModel> pesquisar(PagamentoFiltro filtro, Pageable paginacao);
}

