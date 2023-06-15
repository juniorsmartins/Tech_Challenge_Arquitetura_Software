package com.techchallenge.devnet.core.domain.value_objects;

import com.techchallenge.devnet.core.domain.entities.Cliente;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ClienteSpecification {

  public static Specification<Cliente> consultaDinamica(ClienteFiltro filtro) {

    return ((root, query, criteriaBuilder) -> {

      var predicados = new ArrayList<Predicate>();

      if (ObjectUtils.isNotEmpty(filtro.getId())) {
        var ids = Arrays.asList(filtro.getId().split(","));
        List<Predicate> idPredicates = ids.stream()
          .map(id -> criteriaBuilder.equal(root.get("id"), id))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(idPredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getNome())) {
        var nomes = Arrays.asList(filtro.getNome().split(","));
        List<Predicate> nomePredicates = nomes.stream()
          .map(nome -> criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(nomePredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getCpf())) {
        var cpfs = Arrays.asList(filtro.getCpf().split(","));
        List<Predicate> cpfPredicates = cpfs.stream()
          .map(cpf -> criteriaBuilder.like(root.get("cpf"), "%" + cpf + "%"))
          .collect(Collectors.toList());

        predicados.add(criteriaBuilder.or(cpfPredicates.toArray(new Predicate[0])));
      }

      if (ObjectUtils.isNotEmpty(filtro.getEmail())) {
        predicados.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
          "%".concat(filtro.getEmail().toLowerCase()).concat("%")));
      }

      return criteriaBuilder.and(predicados.toArray(new Predicate[0]));
    });
  }
}

