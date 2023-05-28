package com.techchallenge.devnet.core.application.ports;

import com.techchallenge.devnet.core.domain.filters.ClienteFiltro;
import com.techchallenge.devnet.core.domain.Cliente;
import com.techchallenge.devnet.core.domain.PoliticaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

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

