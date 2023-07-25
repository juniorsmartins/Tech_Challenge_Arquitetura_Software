package com.techchallenge.devnet.interface_adapters.driven_secundario.adapter_saida;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IAdapterSaida {

  <O, D> D converterOrigemParaDestino(O origem, Class<D> destino);

  <O, D> Page<D> converterPaginaOrigemParaPaginaDestino(Page<O> origens, Class<D> destino);

  <O, D> List<D> converterListaOrigemParaListaDestino(List<O> origens, Class<D> destino);
}

