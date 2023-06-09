package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.models.ClienteModel;
import com.techchallenge.devnet.core.domain.objects.filtros.ClienteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IClienteRepositoryPort {

  interface PostRepository {
    ClienteModel salvar(ClienteModel clienteModel);
  }

  interface GetRepository {

    Page<ClienteModel> pesquisar(ClienteFiltro filtro, Pageable paginacao);

    Optional<ClienteModel> consultarPorId(Long id);

    Optional<ClienteModel> consultarPorCpf(String cpf);
  }

  interface DeleteRepository {
    void deletar(ClienteModel clienteModel);
  }
}

