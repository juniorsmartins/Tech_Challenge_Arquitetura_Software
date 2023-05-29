package com.techchallenge.devnet.core.domain.value_objects;

import com.techchallenge.devnet.core.domain.entities.Produto;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public final class ProdutoSpecification {

  public static Specification<Produto> consultaDinamica(ProdutoFiltro filtro) {

    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        predicados.add(criteriaBuilder.equal(root.get("id"), filtro.getId()));
      }

      if (ObjectUtils.isNotEmpty(filtro.getCategoria())) {
        predicados.add(criteriaBuilder.equal(root.get("categoria"), filtro.getCategoria()));
      }

      if (ObjectUtils.isNotEmpty(filtro.getNome())) {
        predicados.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
          "%".concat(filtro.getNome().toLowerCase()).concat("%")));
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

