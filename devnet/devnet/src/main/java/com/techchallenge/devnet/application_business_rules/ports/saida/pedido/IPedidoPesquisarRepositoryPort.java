package com.techchallenge.devnet.application_business_rules.ports.saida.pedido;

import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPedidoPesquisarRepositoryPort {

  Page<PedidoModel> pesquisar(PedidoFiltro filtro, Pageable paginacao);
}

