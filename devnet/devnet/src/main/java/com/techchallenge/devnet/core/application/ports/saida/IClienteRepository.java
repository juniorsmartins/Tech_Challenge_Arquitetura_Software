package com.techchallenge.devnet.core.application.ports.saida;

import com.techchallenge.devnet.core.domain.value_objects.filtros.ClienteFiltro;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IClienteRepository {

  interface PostRepository {
    Cliente salvar(Cliente cliente);
  }

  interface GetRepository {

    Page<Cliente> pesquisar(ClienteFiltro filtro, Pageable paginacao);

    Optional<Cliente> consultarPorId(Long id);

    Optional<Cliente> consultarPorCpf(String cpf);
  }

  interface DeleteRepository {
    void deletar(Cliente cliente);
  }
}

