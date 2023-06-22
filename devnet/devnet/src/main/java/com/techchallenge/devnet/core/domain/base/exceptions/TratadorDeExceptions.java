package com.techchallenge.devnet.core.domain.base.exceptions;

import com.techchallenge.devnet.core.domain.base.exceptions.http_404.RecursoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_409.RegraDeNegocioVioladaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public final class TratadorDeExceptions extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = RegraDeNegocioVioladaException.class)
  public ResponseEntity<Object> tratarRegraDeNegocioViolada(RegraDeNegocioVioladaException regraViolada,
                                                                   WebRequest webRequest) {
    var httpStatus = HttpStatus.CONFLICT;
    var tipoDeErroEnum = ETipoDeErro.REGRA_NEGOCIO_VIOLADA;
    var detalhe = regraViolada.getMessage();

    var retornoDeErro = this.criarMensagemParaRetornarErro(httpStatus, tipoDeErroEnum, detalhe)
      .build();

    return super.handleExceptionInternal(regraViolada, retornoDeErro, new HttpHeaders(),
      httpStatus, webRequest);
  }

  @ExceptionHandler(value = RecursoNaoEncontradoException.class)
  public ResponseEntity<Object> tratarRecursoNaoEncontrado(RecursoNaoEncontradoException recursoNaoEncontrado,
                                                           WebRequest webRequest) {
    var httpStatus = HttpStatus.NOT_FOUND;
    var tipoDeErroEnum = ETipoDeErro.RECURSO_NAO_ENCONTRADO;
    var detalhe = recursoNaoEncontrado.getMessage();

    var retornoDeErro = this.criarMensagemParaRetornarErro(httpStatus, tipoDeErroEnum, detalhe)
      .build();

    return super.handleExceptionInternal(recursoNaoEncontrado, retornoDeErro, new HttpHeaders(),
      httpStatus, webRequest);
  }

  // Sobrescrição de um método comum de ResponseEntityExceptionHandler. Captura exceção quando o tipo de mediaType
  // for incompatível.
  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    return ResponseEntity
      .status(status)
      .headers(headers)
      .build();
  }

  // Método para construção da mensagem de retorno
  private RetornoDeErro.RetornoDeErroBuilder criarMensagemParaRetornarErro(HttpStatusCode httpStatusCode,
                                                                           ETipoDeErro tipoDeErroEnum, String detalhe) {
    return RetornoDeErro.builder()
      .tipo(tipoDeErroEnum.getCaminho())
      .titulo(tipoDeErroEnum.getTitulo())
      .status(httpStatusCode.value())
      .detalhe(detalhe)
//      .instancia()
      .dataHoraErro(Instant.now());
  }

  // Sobrescrição de um método comum de ResponseEntityExceptionHandler. Esse método é chamado por vários outros métodos
  // de tratamento de exceção. Então, ao personalizá-lo, nós interferimos nas respostas de retorno de erro de vários
  // métodos do sistema (ResponseEntityExceptionHandler).
  @Override
  protected  ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
                                                            HttpStatusCode status, WebRequest webRequest) {
    if (body == null) {
      body = RetornoDeErro.builder()
        .status(status.value())
        .detalhe(HttpStatus.valueOf(status.value()).getReasonPhrase()) // Devolve uma descrição sobre o status retornado na resposta
        .dataHoraErro(Instant.now())
        .build();

      return super.handleExceptionInternal(exception, body, headers, status, webRequest);
    } else if (body instanceof String) {

      body = RetornoDeErro.builder()
        .status(status.value())
        .detalhe(body.toString())
        .dataHoraErro(Instant.now())
        .build();
    }

    return super.handleExceptionInternal(exception, body, headers, status, webRequest);
  }
}

