package com.techchallenge.devnet.core.domain.objects.specification;

import com.techchallenge.devnet.adapter.driven_secundario.entities.PagamentoEntity;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.PagamentoFiltroDto;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PagamentoSpecification {

  public static Specification<PagamentoEntity> consultarDinamicamente(PagamentoFiltroDto filtro) {
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

      if (ObjectUtils.isNotEmpty(filtro.getNomeImagemQRCode())) {
        var nomes = Arrays.asList(filtro.getNomeImagemQRCode().split(","));
        List<Predicate> idPredicates = nomes.stream()
          .map(nome -> criteriaBuilder.like(root.get("nomeImagemQRCode"), "%" + nome + "%"))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(idPredicates.toArray(new Predicate[0])));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}

