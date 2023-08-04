package com.techchallenge.devnet.utils;

import com.github.javafaker.Faker;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ClienteDao;
import com.techchallenge.devnet.interface_adapters.driven_secundario.daos.ProdutoDao;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.ClienteDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.ItemPedidoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.PedidoDtoRequest;
import com.techchallenge.devnet.interface_adapters.driver_primario.dtos.requisicao.ProdutoDtoRequest;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.CategoriaEnum;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.FormaPagamentoEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public final class CriadorDeObjetos {

  public static Faker faker = new Faker();

  private static GeradorDeDocs cpfGenerator = new GeradorDeDocs();

  public static ClienteDao.ClienteDaoBuilder gerarClienteDaoBuilder() {

    return ClienteDao.builder()
      .nome(faker.name().fullName())
      .cpf(cpfGenerator.cpf(false))
      .numeroTelefone(faker.numerify("###########"))
      .email(faker.internet().emailAddress())
      .dataNascimentoLocalDate(LocalDate.now());
  }

  public static ClienteDtoRequest.ClienteDtoRequestBuilder gerarClienteDtoRequestBuilder() {

    return ClienteDtoRequest.builder()
      .nome(faker.name().fullName())
      .cpf(cpfGenerator.cpf(false))
      .numeroTelefone(faker.numerify("###########"))
      .email(faker.internet().emailAddress())
      .dataNascimentoString("1" + faker.numerify("#") + "/10/19" + faker.numerify("##"));
  }

  public static ProdutoDao.ProdutoDaoBuilder gerarProdutoDaoBuilder() {

    var preco = (Math.random() + 1) * 12;

    return ProdutoDao.builder()
      .nome(faker.food().fruit())
      .categoria(CategoriaEnum.ACOMPANHAMENTO)
      .descricao(faker.lorem().characters(10, 250))
      .preco(BigDecimal.valueOf(preco).setScale(2, RoundingMode.HALF_UP));
  }

  public static ProdutoDtoRequest.ProdutoDtoRequestBuilder gerarProdutoDtoRequestBuilder() {

    var preco = (Math.random() + 1) * 15;

    return ProdutoDtoRequest.builder()
      .nome(faker.food().fruit())
      .categoria(CategoriaEnum.ACOMPANHAMENTO)
      .descricao(faker.lorem().characters(10, 250))
      .preco(BigDecimal.valueOf(preco).setScale(2, RoundingMode.HALF_UP));
  }

  public static PedidoDtoRequest.PedidoDtoRequestBuilder gerarPedidoDtoRequestBuilder() {

    // Preencher com ItemPedidoDtoRequest

    return PedidoDtoRequest.builder()
      .formaPagamento(FormaPagamentoEnum.PIX);
  }

  public static ItemPedidoDtoRequest.ItemPedidoDtoRequestBuilder gerarItemPedidoDtoRequestBuilder() {

    // Preencher com ProdutoDtoResumo

    return ItemPedidoDtoRequest.builder()
      .quantidade(Integer.parseInt(faker.numerify("#")));
  }

  public static EmailDtoRequest.EmailDtoRequestBuilder gerarEmailDtoRequestBuilder() {

    return EmailDtoRequest.builder()
      .ownerRef(faker.name().fullName())
      .emailFrom("techchallenge6@gmail.com")
      .emailTo("juniorsoaresmartins@gmail.com")
      .subject("DevNet - Teste Automatizado")
      .text("Olá! Somos a DevNet. Venha nos conhecer.");
  }
}

