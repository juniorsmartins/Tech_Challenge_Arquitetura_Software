package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.EmailDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.EmailDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IEmailService;
import com.techchallenge.devnet.core.application.ports.saida.IClienteRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.IEmailRepository;
import com.techchallenge.devnet.core.application.ports.saida.IPedidoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.entities.Email;
import com.techchallenge.devnet.core.domain.entities.enums.StatusEmailEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class EmailPostService implements IEmailService.EnviarService {

  @Autowired
  private IMapper mapper;

  @Autowired
  private IPedidoRepository.GetRepository pedidoGetRepository;

  @Autowired
  private IClienteRepositoryPort.GetRepository clienteGetRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private IEmailRepository.PostRepository emailPostRepository;

  @Override
  public EmailDtoResponse enviar(EmailDtoRequest dtoRequest) {

    return Optional.of(dtoRequest)
      .map(dto -> this.mapper.converterDtoRequestParaEntidade(dto, Email.class))
      .map(this::validarPedido)
      .map(this::validarCliente)
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
          log.info(MensagemPadrao.EMAIL_NAO_ENVIADO_POR_EXCECAO);
          email.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
          this.emailPostRepository.salvar(email);
        }

        return email;
      })
      .map(email -> this.mapper.converterEntidadeParaDtoResponse(email, EmailDtoResponse.class))
      .orElseThrow();
  }

  private Email validarPedido(Email email) {
    var idPedido = email.getPedido().getId();

    var pedido = this.pedidoGetRepository.consultarPorId(idPedido)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });

    email.setPedido(pedido);

    return email;
  }

  private Email validarCliente(Email email) {

    if (ObjectUtils.isNotEmpty(email.getPedido().getCliente())) {
      var idCliente = email.getPedido().getCliente().getId();

      var cliente = this.clienteGetRepository.consultarPorId(idCliente)
        .orElseThrow(() -> {
          log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, idCliente));
          throw new ClienteNaoEncontradoException(idCliente);
        });

      email.getPedido().setCliente(cliente);
    }

    return email;
  }
}

