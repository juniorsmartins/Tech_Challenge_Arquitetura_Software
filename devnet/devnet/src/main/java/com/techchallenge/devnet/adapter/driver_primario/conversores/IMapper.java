package com.techchallenge.devnet.adapter.driver_primario.conversores;

import org.springframework.data.domain.Page;

public interface IMapper {

  <D, E> E converterDtoRequestParaEntidade(D dtoRequest, Class<E> eClass);

  <E, R> R converterEntidadeParaDtoResponse(E entity, Class<R> eClass);

  <E, R> Page<R> converterPaginaDeEntidadeParaPaginaDtoResponse(Page<E> entidades, Class<R> eClass);
}

