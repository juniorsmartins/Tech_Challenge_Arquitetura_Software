package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.EmailDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IEmailService;
import com.techchallenge.devnet.core.application.ports.saida.IEmailRepository;
import com.techchallenge.devnet.core.domain.entities.Email;
import com.techchallenge.devnet.core.domain.entities.enums.StatusEmailEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailPostService implements IEmailService.EnviarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private IEmailRepository.PostRepository emailPostRepository;

  @Override
  public EmailDtoResponse enviar(EmailDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Email.class))
      .map(email -> {
        email.setSendDataEmail(LocalDateTime.now());

        try {
          SimpleMailMessage mensagem = new SimpleMailMessage();
          mensagem.setFrom(email.getEmailFrom());
          mensagem.setTo(email.getEmailTo());
          mensagem.setSubject(email.getSubject());
          mensagem.setText(email.getText());
          javaMailSender.send(mensagem);

          email.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException mailException) {
          email.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
          this.emailPostRepository.salvar(email);
        }

        return email;
      })
      .map(email -> this.mapper.converterEntidadeParaDtoResponse(email, EmailDtoResponse.class))
      .orElseThrow();
  }
}

