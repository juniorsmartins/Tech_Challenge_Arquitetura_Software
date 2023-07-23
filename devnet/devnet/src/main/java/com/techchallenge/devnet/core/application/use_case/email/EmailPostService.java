package com.techchallenge.devnet.core.application.use_case.email;

import com.techchallenge.devnet.core.application.exceptions.MensagemPadrao;
import com.techchallenge.devnet.core.application.ports.entrada.email.IEmailEnviarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtilsCliente;
import com.techchallenge.devnet.core.domain.base.utilitarios.IUtilsPedido;
import com.techchallenge.devnet.core.domain.models.EmailModel;
import com.techchallenge.devnet.core.domain.models.enums.StatusEmailEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class EmailPostService implements IEmailEnviarServicePort {

  private final IUtilsCliente utilsCliente;

  private final IUtilsPedido utilsPedido;

  private final JavaMailSender javaMailSender;

  private final IEmailSalvarRepositoryPort emailSalvarRepository;

  public EmailPostService(IUtilsCliente utilsCliente,
                          IUtilsPedido utilsPedido,
                          JavaMailSender javaMailSender,
                          IEmailSalvarRepositoryPort emailSalvarRepository) {
    this.utilsCliente = utilsCliente;
    this.utilsPedido = utilsPedido;
    this.javaMailSender = javaMailSender;
    this.emailSalvarRepository = emailSalvarRepository;
  }

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

    var pedido = this.utilsPedido.validarPedido(emailModel.getPedido());
    emailModel.setPedido(pedido);
    return emailModel;
  }

  private EmailModel validarCliente(EmailModel emailModel) {

    if (ObjectUtils.isNotEmpty(emailModel.getPedido().getCliente())) {
      var cliente = this.utilsCliente.validarCliente(emailModel.getPedido().getCliente());
      emailModel.getPedido().setCliente(cliente);
    }
    return emailModel;
  }
}

