package com.techchallenge.devnet.core.application.use_case.email;

import com.techchallenge.devnet.core.application.ports.entrada.email.IEmailEnviarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.cliente.IClienteConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.pedido.IPedidoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ClienteNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.PedidoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusEmailEnum;
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
public class EmailEnviarService implements IEmailEnviarServicePort {

  @Autowired
  private IPedidoConsultarPorIdRepositoryPort pedidoGetRepository;

  @Autowired
  private IClienteConsultarPorIdRepositoryPort clienteConsultarPorIdRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  @Autowired
  private IEmailSalvarRepositoryPort emailSalvarRepository;

  @Override
  public EmailModel enviar(EmailModel emailModel) {

    return Optional.of(emailModel)
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
          this.emailSalvarRepository.salvar(email);
        }

        return email;
      })
      .orElseThrow();
  }

  private EmailModel validarPedido(EmailModel emailModel) {
    var idPedido = emailModel.getPedido().getId();

    var pedido = this.pedidoGetRepository.consultarPorId(idPedido)
      .orElseThrow(() -> {
        log.info(String.format(MensagemPadrao.PEDIDO_NAO_ENCONTRADO, idPedido));
        throw new PedidoNaoEncontradoException(idPedido);
      });

    emailModel.setPedido(pedido);

    return emailModel;
  }

  private EmailModel validarCliente(EmailModel email) {

    if (ObjectUtils.isNotEmpty(email.getPedido().getCliente())) {
      var idCliente = email.getPedido().getCliente().getId();

      var cliente = this.clienteConsultarPorIdRepository.consultarPorId(idCliente)
        .orElseThrow(() -> {
          log.info(String.format(MensagemPadrao.CLIENTE_NAO_ENCONTRADO, idCliente));
          throw new ClienteNaoEncontradoException(idCliente);
        });

      email.getPedido().setCliente(cliente);
    }

    return email;
  }
}

