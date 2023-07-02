package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.ProdutoModel;
import com.techchallenge.devnet.core.domain.value_objects.filtros.ProdutoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoGetService implements IProdutoServicePort.GetService {

  @Autowired
  private IProdutoRepositoryPort.GetRepository produtoGetRepository;

  @Override
  public Page<ProdutoModel> pesquisar(final ProdutoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.produtoGetRepository.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

