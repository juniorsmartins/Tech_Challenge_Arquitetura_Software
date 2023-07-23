package com.techchallenge.devnet.core.domain.base.value_objects;

import com.techchallenge.devnet.core.domain.base.exceptions.http_400.TelefoneInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@EqualsAndHashCode(of = {"numeroTelefone"})
public final class Telefone implements Serializable {

  public static final long serialVersionUID = 1L;

  private static final Pattern VALID_PHONE_NUMBER = Pattern.compile("^\\d{10,11}$");

  private String numeroTelefone;

  public Telefone(String numeroTelefone) {
    if (!this.ehValido(numeroTelefone)) {
      throw new TelefoneInvalidoException(numeroTelefone);
    }
    this.numeroTelefone = numeroTelefone;
  }

  public boolean ehValido(String numeroTelefone) {
    Matcher matcher = VALID_PHONE_NUMBER.matcher(numeroTelefone);
    return matcher.find();
  }
}

