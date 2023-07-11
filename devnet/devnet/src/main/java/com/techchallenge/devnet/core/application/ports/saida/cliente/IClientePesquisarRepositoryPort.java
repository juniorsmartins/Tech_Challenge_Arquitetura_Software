package com.techchallenge.devnet.core.application.ports.saida.cliente;

import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClientePesquisarRepositoryPort {

  Page<ClienteModel> pesquisar(ClienteFiltro filtro, Pageable paginacao);
}

