package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IPedidoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepositoryPort;
import com.techchallenge.devnet.core.domain.models.PedidoModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusPedidoEnum;
import com.techchallenge.devnet.core.domain.objects.filtros.PedidoFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoGetService implements IPedidoServicePort.GetService {

  @Autowired
  private IPedidoRepositoryPort.GetRepository pedidoGetRepository;

  @Transactional(readOnly = true)
  @Override
  public Page<PedidoModel> pesquisar(final PedidoFiltro filtro, final Pageable paginacao) {

    return Optional.of(filtro)
      .map(parametrosDePesquisa -> this.pedidoGetRepository.pesquisar(parametrosDePesquisa, paginacao))
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

    return this.pedidoGetRepository.listar()
      .stream()
      .filter(model -> !model.getStatusPedido().equals(StatusPedidoEnum.CANCELADO))
      .filter(model -> !model.getStatusPedido().equals(StatusPedidoEnum.FINALIZADO))
      .sorted(statusComparator.thenComparing(PedidoModel::getDataHoraCadastro))
      .collect(Collectors.toList());
  }
}

