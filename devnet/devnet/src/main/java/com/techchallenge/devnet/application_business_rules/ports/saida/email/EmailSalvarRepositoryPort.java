package com.techchallenge.devnet.application_business_rules.ports.saida.email;

import com.techchallenge.devnet.enterprise_business_rules.models.EmailModel;

public interface EmailSalvarRepositoryPort {

    EmailModel salvar(EmailModel email);
}

