package com.techchallenge.devnet.application_business_rules.use_case.pedido;

import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.PedidoListarOrdenadoServicePort;
import com.techchallenge.devnet.application_business_rules.ports.entrada.pedido.PedidoPesquisarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoListarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.pedido.PedidoPesquisarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.PedidoModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusPedidoEnum;
import com.techchallenge.devnet.enterprise_business_rules.objects.filtros.PedidoFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoGetServiceImpl implements PedidoPesquisarServicePort, PedidoListarOrdenadoServicePort {

  private final PedidoPesquisarRepositoryPort repositorioPesquisar;

  private final PedidoListarRepositoryPort repositorioListar;

  public PedidoGetServiceImpl(PedidoPesquisarRepositoryPort repositorioPesquisar,
                              PedidoListarRepositoryPort repositorioListar) {
    this.repositorioPesquisar = repositorioPesquisar;
    this.repositorioListar = repositorioListar;
  }

  @Transactional(readOnly = true)
  @Override
  public Page<PedidoModel> pesquisar(final PedidoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.repositorioPesquisar.pesquisar(parametrosDePesquisa, paginacao))
      .orElseThrow();
  }

  @Override
  public List<PedidoModel> listarOrdenadoPorStatusAndDataHoraCadastro() {

    Comparator<PedidoModel> statusComparator = Comparator.comparing((PedidoModel model) -> {
      StatusPedidoEnum status = model.getStatusPedido();
      if (status == StatusPedidoEnum.PRONTO) {
        return 0;
      } else if (status == StatusPedidoEnum.PREPARACAO) {
        return 1;
      } else if (status == StatusPedidoEnum.RECEBIDO) {
        return 2;
      } else {
        return 3;
      }
    });

    return this.repositorioListar.listar()
      .stream()
      .filter(model -> !model.getStatusPedido().equals(StatusPedidoEnum.CANCELADO))
      .filter(model -> !model.getStatusPedido().equals(StatusPedidoEnum.FINALIZADO))
      .sorted(statusComparator.thenComparing(PedidoModel::getDataHoraCadastro))
      .collect(Collectors.toList());
  }
}

