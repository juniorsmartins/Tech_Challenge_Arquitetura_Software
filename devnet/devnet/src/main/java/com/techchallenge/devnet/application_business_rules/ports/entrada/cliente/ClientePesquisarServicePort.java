package com.techchallenge.devnet.application_business_rules.ports.entrada.cliente;

import com.techchallenge.devnet.enterprise_business_rules.models.ClienteModel;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientePesquisarServicePort {

  Page<ClienteModel> pesquisar(ClienteFiltro filtro, Pageable paginacao);
}

