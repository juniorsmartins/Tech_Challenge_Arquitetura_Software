package com.techchallenge.devnet.exceptions;

import com.techchallenge.devnet.exceptions.http_409.RegraDeNegocioVioladaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.OffsetDateTime;

@RestControllerAdvice
public final class TratadorDeExceptions extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = RegraDeNegocioVioladaException.class)
  public ResponseEntity<RetornoDeErro> regraDeNegocioViolada(RegraDeNegocioVioladaException regraViolada, WebRequest webRequest) {

    var httpStatus = HttpStatus.CONFLICT;
    var tipoDeErroEnum = TipoDeErroEnum.REGRA_VIOLADA;
    var detalhe = regraViolada.getMessage();

    var retornoDeErro = this.criarMensagemParaRetornarErro(httpStatus, tipoDeErroEnum, detalhe).build();

    return null;
  }

  private RetornoDeErro.RetornoDeErroBuilder criarMensagemParaRetornarErro(HttpStatusCode httpStatusCode,
                                                                           TipoDeErroEnum tipoDeErroEnum,
                                                                           String detalhe) {

    return RetornoDeErro.builder()
      .tipo(tipoDeErroEnum.getCaminho())
      .titulo(tipoDeErroEnum.getTitulo())
      .status(httpStatusCode.value())
      .detalhe(detalhe)
//      .instancia()
      .dataHoraErro(Instant.now());

  }

  @Override
  protected  ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
                                                            HttpStatusCode status, WebRequest webRequest) {

//    if (body == null) {
//      body = RetornoDeErro.builder()
//        .status(status.value())
//        .
//        .tipoDeErro(HttpStatus.valueOf(status.value()).getReasonPhrase()) // Devolve uma descrição sobre o status retornado na resposta
//        .dataHora(OffsetDateTime.now())
//        .build();
//
//    } else if (body instanceof String) {
//      body = RetornoDeErro.builder()
//        .codigoHttp(status.value())
//        .tipoDeErro(body.toString())
//        .dataHora(OffsetDateTime.now())
//        .build();
//    }

    return super.handleExceptionInternal(exception, body, headers, status, webRequest);
  }
}

