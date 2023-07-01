package com.techchallenge.devnet.core.application.ports.entrada;

import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IClienteServicePort {

  interface PostService {
    ClienteModel cadastrar(ClienteModel cliente);
  }

  interface PutService {
    ClienteModel atualizar(Long id, ClienteModel clienteModel);
  }

  interface GetService {
    Page<ClienteModel> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface DeleteService {
    void deletar(Long id);
  }
}

