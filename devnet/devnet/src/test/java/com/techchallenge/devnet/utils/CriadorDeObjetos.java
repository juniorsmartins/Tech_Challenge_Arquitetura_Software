package com.techchallenge.devnet.utils;

import com.github.javafaker.Faker;
import com.techchallenge.devnet.adapter.driver.dtos.request.ClienteDtoRequest;
import com.techchallenge.devnet.core.domain.entities.Cliente;

public final class CriadorDeObjetos {

  public static Faker faker = new Faker();

  private static CpfGenerator cpfGenerator = new CpfGenerator();

  public static Cliente.ClienteBuilder gerarClienteBuilder() {

    return Cliente.builder()
      .nome(faker.name().fullName())
      .cpf(cpfGenerator.cpf(false))
      .email(faker.internet().emailAddress());
  }

  public static ClienteDtoRequest.ClienteDtoRequestBuilder gerarClienteDtoRequestBuilder() {

    return ClienteDtoRequest.builder()
      .nome(faker.name().fullName())
      .cpf(cpfGenerator.cpf(false))
      .email(faker.internet().emailAddress());
  }
}

