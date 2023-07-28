package com.techchallenge.devnet.enterprise_business_rules.models;

import com.techchallenge.devnet.enterprise_business_rules.base.value_objects.CadastroPessoaFisica;
import com.techchallenge.devnet.enterprise_business_rules.base.value_objects.CorreioEletronico;
import com.techchallenge.devnet.enterprise_business_rules.base.value_objects.DataNascimento;
import com.techchallenge.devnet.enterprise_business_rules.base.value_objects.Telefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.time.LocalDate;
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

  private Telefone numeroTelefone;

  private CorreioEletronico email;

  private DataNascimento dataNascimento;

  private OffsetDateTime dataHoraCadastro;

  private OffsetDateTime dataHoraAtualizacao;

  public void setCpf(String cpf) {
    this.cpf = new CadastroPessoaFisica(cpf);
  }

  public String getCpf() {
    return this.cpf.getCpf();
  }

  public void setNumeroTelefone(String numeroTelefone) {
    if (ObjectUtils.isNotEmpty(numeroTelefone)) {
      this.numeroTelefone = new Telefone(numeroTelefone);
      return;
    }
    this.numeroTelefone = null;
  }

  public String getNumeroTelefone() {
    if (ObjectUtils.isNotEmpty(this.numeroTelefone)) {
      return this.numeroTelefone.getNumeroTelefone();
    }
    return null;
  }

  public void setEmail(String email) {
    this.email = new CorreioEletronico(email);
  }

  public String getEmail() {
    return this.email.getEmail();
  }

  public void setDataNascimentoString(String dataNascimento) {
    if (ObjectUtils.isNotEmpty(dataNascimento)) {
      this.dataNascimento = new DataNascimento(dataNascimento);
    }
  }

  public String getDataNascimento() {
    if (ObjectUtils.isNotEmpty(this.dataNascimento)) {
      return this.dataNascimento.getDataNascimentoString();
    }
    return null;
  }

  public void setDataNascimentoLocalDate(LocalDate dataNascimento) {
    if (ObjectUtils.isNotEmpty(dataNascimento)) {
      this.dataNascimento = new DataNascimento(dataNascimento);
    }
  }

  public LocalDate getDataNascimentoLocalDate() {
    if (ObjectUtils.isNotEmpty(this.dataNascimento)) {
      return this.dataNascimento.getDataNascimentoLocalDate();
    }
    return null;
  }
}

