package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.EmailDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IEmailService;
import com.techchallenge.devnet.core.application.ports.saida.IEmailRepository;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.entities.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailPostService implements IEmailService.EnviarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IEmailRepository.PostRepository emailPostRepository;

  @Override
  public EmailDtoResponse enviar(EmailDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Email.class))
      .map(this.emailPostRepository::salvar)
      .map(email -> this.mapper.converterEntidadeParaDtoResponse(email, EmailDtoResponse.class))
      .orElseThrow();
  }
}

