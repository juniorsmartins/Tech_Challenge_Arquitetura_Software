package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.pagamento.IPagamentoPesquisarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.pagamento.IPagamentoPesquisarRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PagamentoModel;
import com.techchallenge.devnet.core.domain.objects.filtros.PagamentoFiltro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PagamentoPesquisarService implements IPagamentoPesquisarServicePort {

  @Autowired
  private IPagamentoPesquisarRepositoryPort pagamentoPesquisarRepository;

  @Override
  public Page<PagamentoModel> pesquisar(final PagamentoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pagamentoPesquisarRepository.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }
}

