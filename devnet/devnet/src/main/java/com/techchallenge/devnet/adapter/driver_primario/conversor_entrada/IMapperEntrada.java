package com.techchallenge.devnet.adapter.driver_primario.conversor_entrada;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IMapperEntrada {

  <O, D> D converterOrigemParaDestino(O origem, Class<D> destino);

  <O, D> Page<D> converterPaginaOrigemParaPaginaDestino(Page<O> origens, Class<D> destino);

  <O, D> List<D> converterListaOrigemParaListaDestino(List<O> origens, Class<D> destino);
}

