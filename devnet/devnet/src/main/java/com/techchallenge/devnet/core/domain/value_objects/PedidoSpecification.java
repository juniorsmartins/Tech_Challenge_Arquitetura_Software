package com.techchallenge.devnet.core.domain.value_objects;

import com.techchallenge.devnet.core.domain.entities.Pedido;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PedidoSpecification {

  public static Specification<Pedido> consultaDinamica(PedidoFiltro filtro) {

    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        predicados.add(criteriaBuilder.equal(root.get("id"), filtro.getId()));
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

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}

