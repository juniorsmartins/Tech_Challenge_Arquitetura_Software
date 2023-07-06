package com.techchallenge.devnet.core.domain.objects.specification;

import com.techchallenge.devnet.adapter.driven_secundario.entities.ProdutoEntity;
import com.techchallenge.devnet.adapter.driver_primario.dtos.filtros.ProdutoFiltroDto;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ProdutoSpecification {

  public static Specification<ProdutoEntity> consultarDinamicamente(ProdutoFiltroDto filtro) {

    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        var ids = Arrays.asList(filtro.getId().split(","));
        List<Predicate> idPredicates = ids.stream()
          .map(id -> criteriaBuilder.equal(root.get("id"), id))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(idPredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getCategoria())) {
        predicados.add(criteriaBuilder.equal(root.get("categoria"), filtro.getCategoria()));
      }

      if (ObjectUtils.isNotEmpty(filtro.getNome())) {
        var nomes = Arrays.asList(filtro.getNome().split(","));
        List<Predicate> nomePredicates = nomes.stream()
          .map(nome -> criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(nomePredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getDescricao())) {
        predicados.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")),
          "%".concat(filtro.getDescricao().toLowerCase()).concat("%")));
      }

      if (ObjectUtils.isNotEmpty(filtro.getPreco())) {
        predicados.add(criteriaBuilder.equal(root.get("preco"), filtro.getPreco()));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}

