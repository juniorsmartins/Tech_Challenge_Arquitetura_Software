package com.techchallenge.devnet.adapter.driver_primario.conversores;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public final class MapperImpl implements IMapper {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public <O, D> D converterOrigemParaDestino(O origem, Class<D> destino) {
    return this.modelMapper.map(origem, destino);
  }

  @Override
  public <O, D> Page<D> converterPaginaOrigemParaPaginaDestino(Page<O> origens, Class<D> destino) {
    return origens.map(origem -> this.modelMapper.map(origem, destino));
  }

  @Override
  public <D, E> E converterDtoRequestParaEntidade(D dtoRequest, Class<E> eClass) {
    return this.modelMapper.map(dtoRequest, eClass);
  }

  @Override
  public <E, R> R converterEntidadeParaDtoResponse(E entity, Class<R> eClass) {
    return this.modelMapper.map(entity, eClass);
  }

  @Override
  public <E, R> Page<R> converterPaginaDeEntidadeParaPaginaDtoResponse(Page<E> entidades, Class<R> eClass) {
    return entidades.map(entidade -> this.converterEntidadeParaDtoResponse(entidade, eClass));
  }
}
