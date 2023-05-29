package com.techchallenge.devnet.core.domain.base.mappers;

import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.ProdutoDtoResponse;
import com.techchallenge.devnet.core.domain.entities.Produto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@Mapper(componentModel = "spring")
public interface IProdutoMapper {

  Produto converterDtoRequestParaEntidade(ProdutoDtoRequest dtoRequest);

  ProdutoDtoResponse converterEntidadeParaDtoResponse(Produto produto);

  default Page<ProdutoDtoResponse> converterPaginaDeEntidadesParaPaginaDeDtosResponse(Page<Produto> pageProduto) {
    var produtoDTOs = pageProduto.getContent()
      .stream()
      .map(this::converterEntidadeParaDtoResponse)
      .toList();

    return new PageImpl<>(produtoDTOs, pageProduto.getPageable(), pageProduto.getTotalElements());
  }
}

