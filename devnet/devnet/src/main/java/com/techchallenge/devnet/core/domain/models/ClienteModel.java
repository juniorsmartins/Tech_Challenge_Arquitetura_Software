package com.techchallenge.devnet.core.domain.models;

import com.techchallenge.devnet.core.domain.base.value_objects.CadastroPessoaFisica;
import com.techchallenge.devnet.core.domain.base.value_objects.CorreioEletronico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public final class ClienteModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String nome;

  private CadastroPessoaFisica cpf;

  private CorreioEletronico email;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;

  public void setCpf(String cpf) {
    this.cpf = new CadastroPessoaFisica(cpf);
  }

  public String getCpf() {
    return this.cpf.getCpf();
  }

  public void setEmail(String email) {
    this.email = new CorreioEletronico(email);
  }

  public String getEmail() {
    return this.email.getEmail();
  }
}

