package com.techchallenge.devnet.core.domain.objects.specification;

import com.techchallenge.devnet.adapter.driven_secundario.daos.PedidoDao;
import com.techchallenge.devnet.adapter.driver_primario.filtros.PedidoFiltroDto;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PedidoSpecification {

  public static Specification<PedidoDao> consultarDinamicamente(PedidoFiltroDto filtro) {

    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        var ids = Arrays.asList(filtro.getId().split(","));
        List<Predicate> idPredicates = ids.stream()
          .map(id -> criteriaBuilder.equal(root.get("id"), id))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(idPredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getStatusPedido())) {
        predicados.add(criteriaBuilder.equal(root.get("statusPedido"), filtro.getStatusPedido()));
      }

      if (ObjectUtils.isNotEmpty(filtro.getCliente())) {
        var cpfs = Arrays.asList(filtro.getCliente().split(","));
        List<Predicate> cpfPredicates = cpfs.stream()
          .map(cpf -> criteriaBuilder.like(root.get("cliente").get("cpf"), "%" + cpf + "%"))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(cpfPredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getFormaPagamento())) {
        predicados.add(criteriaBuilder.equal(root.get("formaPagamento"), filtro.getFormaPagamento()));
      }


      // ---------- FILTRO DE PAGAMENTO ----------//
      if (ObjectUtils.isNotEmpty(filtro.getPagamento()) &&
        ObjectUtils.isNotEmpty(filtro.getPagamento().getStatusPagamento())) {

        predicados.add(criteriaBuilder.equal(root.get("pagamento").get("statusPagamento"),
          filtro.getPagamento().getStatusPagamento()));
      }


      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}

