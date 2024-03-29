package com.techchallenge.devnet.interface_adapters.driver_primario.adapter_entrada;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class AdapterEntradaImpl implements AdapterEntrada {

  private final ModelMapper modelMapper;

  public AdapterEntradaImpl(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  @Override
  public <O, D> D converterOrigemParaDestino(O origem, Class<D> destino) {
    return this.modelMapper.map(origem, destino);
  }

  @Override
  public <O, D> Page<D> converterPaginaOrigemParaPaginaDestino(Page<O> origens, Class<D> destino) {
    return origens.map(origem -> this.modelMapper.map(origem, destino));
  }

  @Override
  public <O, D> List<D> converterListaOrigemParaListaDestino(List<O> origens, Class<D> destino) {
    return origens.stream()
      .map(origem -> this.modelMapper.map(origem, destino))
      .collect(Collectors.toList());
  }
}

