package com.techchallenge.devnet.application_business_rules.use_case.email;

import com.techchallenge.devnet.application_business_rules.exceptions.MensagemPadrao;
import com.techchallenge.devnet.application_business_rules.ports.entrada.email.IEmailEnviarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.email.IEmailSalvarRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsCliente;
import com.techchallenge.devnet.enterprise_business_rules.base.utilitarios.UtilsPedido;
import com.techchallenge.devnet.enterprise_business_rules.models.EmailModel;
import com.techchallenge.devnet.enterprise_business_rules.models.enums.StatusEmailEnum;
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

  private final UtilsCliente utilsCliente;

  private final UtilsPedido utilsPedido;

  private final JavaMailSender javaMailSender;

  private final IEmailSalvarRepositoryPort emailSalvarRepository;

  public EmailPostService(UtilsCliente utilsCliente,
                          UtilsPedido utilsPedido,
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

