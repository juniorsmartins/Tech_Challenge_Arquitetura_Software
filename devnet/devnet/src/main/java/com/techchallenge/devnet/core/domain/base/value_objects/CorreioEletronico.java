package com.techchallenge.devnet.core.domain.base.value_objects;

import com.techchallenge.devnet.core.domain.base.exceptions.http_400.EmailInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
public final class CorreioEletronico implements Serializable {

  public static final long serialVersionUID = 1L;

  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  private String email;

  public CorreioEletronico(String email) {
    if (!this.ehValido(email)) {
      throw new EmailInvalidoException(email);
    }
    this.email = email;
  }

  public boolean ehValido(String email) {
    Pattern pattern = Pattern.compile(EMAIL_REGEX);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}

