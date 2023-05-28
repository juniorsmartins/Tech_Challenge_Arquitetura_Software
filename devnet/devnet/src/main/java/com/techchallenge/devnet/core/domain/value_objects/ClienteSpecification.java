package com.techchallenge.devnet.core.domain.value_objects;

import com.techchallenge.devnet.core.domain.entities.Cliente;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public final class ClienteSpecification {

  public static Specification<Cliente> consultaDinamica(ClienteFiltro filtro) {

    return (((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        predicados.add(criteriaBuilder.equal(root.get("id"), filtro.getId()));
      }

      if (ObjectUtils.isNotEmpty(filtro.getNome())) {
        predicados.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")),
          "%".concat(filtro.getNome().toLowerCase()).concat("%")));
      }

      if (ObjectUtils.isNotEmpty(filtro.getCpf())) {
        predicados.add(criteriaBuilder.like(root.get("cpf"),
          "%".concat(filtro.getCpf()).concat("%")));
      }

      if (ObjectUtils.isNotEmpty(filtro.getEmail())) {
        predicados.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
          "%".concat(filtro.getEmail().toLowerCase()).concat("%")));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    }));
  }
}

