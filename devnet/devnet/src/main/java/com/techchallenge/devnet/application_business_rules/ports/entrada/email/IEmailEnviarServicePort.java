package com.techchallenge.devnet.application_business_rules.ports.entrada.email;

import com.techchallenge.devnet.enterprise_business_rules.models.EmailModel;

public interface IEmailEnviarServicePort {

  EmailModel enviar(EmailModel emailModel);
}

