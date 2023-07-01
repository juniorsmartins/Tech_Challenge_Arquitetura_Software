package com.techchallenge.devnet.utils;

import com.github.javafaker.Faker;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.core.domain.entities.ClienteModel;
import com.techchallenge.devnet.core.domain.entities.Produto;
import com.techchallenge.devnet.core.domain.entities.enums.CategoriaEnum;

import java.math.BigDecimal;

public final class CriadorDeObjetos {

  public static Faker faker = new Faker();

  private static CpfGenerator cpfGenerator = new CpfGenerator();

  public static ClienteModel.ClienteBuilder gerarClienteBuilder() {

    return ClienteModel.builder()
      .nome(faker.name().fullName())
      .cpf(cpfGenerator.cpf(false))
      .email(faker.internet().emailAddress());
  }

  public static Produto.ProdutoBuilder gerarProdutoBuilder() {

    var preco = (Math.random() + 1) * 12;

    return Produto.builder()
      .nome(faker.food().fruit())
      .categoria(CategoriaEnum.ACOMPANHAMENTO)
      .descricao(faker.lorem().characters(10, 250))
      .preco(BigDecimal.valueOf(preco));
  }

  public static ClienteDtoRequest.ClienteDtoRequestBuilder gerarClienteDtoRequestBuilder() {

    return ClienteDtoRequest.builder()
      .nome(faker.name().fullName())
      .cpf(cpfGenerator.cpf(false))
      .email(faker.internet().emailAddress());
  }

  public static ProdutoDtoRequest.ProdutoDtoRequestBuilder gerarProdutoDtoRequestBuilder() {

    var preco = (Math.random() + 1) * 15;

    return ProdutoDtoRequest.builder()
      .nome(faker.food().fruit())
      .categoria(CategoriaEnum.ACOMPANHAMENTO)
      .descricao(faker.lorem().characters(10, 250))
      .preco(BigDecimal.valueOf(preco));
  }
}

