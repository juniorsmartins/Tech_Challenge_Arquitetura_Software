package com.techchallenge.devnet.core.domain.ports_repositories;

import com.techchallenge.devnet.core.domain.value_objects.ClienteFiltro;
import com.techchallenge.devnet.core.domain.entities.Cliente;
import com.techchallenge.devnet.core.domain.entities.PoliticaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PoliticaClienteRepository {

  interface ClienteSalvarRepository<E extends PoliticaEntity> {
    E salvar(E entidade);
  }

  interface ClientePesquisarRepository {
    Page<Cliente> pesquisar(ClienteFiltro filtro, Pageable paginacao);
  }

  interface ClienteDeletarRepository {
    void deletar(Cliente cliente);
  }

  interface ClienteConsultarRepository {
    Optional<Cliente> consultarPorId(Long id);

    Optional<Cliente> consultarPorCpf(String cpf);
  }
}

