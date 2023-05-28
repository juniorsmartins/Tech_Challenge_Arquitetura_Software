package com.techchallenge.devnet.repositories;

import com.techchallenge.devnet.filters.ClienteFiltro;
import com.techchallenge.devnet.models.Cliente;
import com.techchallenge.devnet.models.PoliticaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PoliticaRepository {

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

