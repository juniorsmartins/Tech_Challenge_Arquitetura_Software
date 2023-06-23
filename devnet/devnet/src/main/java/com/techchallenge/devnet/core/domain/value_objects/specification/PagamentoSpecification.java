package com.techchallenge.devnet.core.domain.value_objects.specification;

import com.techchallenge.devnet.core.domain.entities.Pagamento;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PagamentoSpecification {

  public static Specification<Pagamento> consultarDinamicamente(PagamentoFiltro filtro) {
    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        var ids = Arrays.asList(filtro.getId().split(","));
        List<Predicate> idPredicates = ids.stream()
          .map(id -> criteriaBuilder.equal(root.get("id"), id))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(idPredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getStatusPagamento())) {
        predicados.add(criteriaBuilder.equal(root.get("statusPagamento"), filtro.getStatusPagamento()));
      }

      if (ObjectUtils.isNotEmpty(filtro.getPedido())) {
        var ids = Arrays.asList(filtro.getPedido().split(","));
        List<Predicate> idPredicates = ids.stream()
          .map(id -> criteriaBuilder.equal(root.get("cliente").get("id"), id))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(idPredicates.toArray(new Predicate[0])));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}

