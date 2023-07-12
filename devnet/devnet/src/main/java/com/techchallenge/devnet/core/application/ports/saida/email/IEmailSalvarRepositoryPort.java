package com.techchallenge.devnet.core.application.ports.saida.email;

import com.techchallenge.devnet.core.domain.models.EmailModel;

public interface IEmailSalvarRepositoryPort {

    EmailModel salvar(EmailModel email);
}

