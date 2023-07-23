package com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ClienteDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank
  @Length(max = 100)
  private String nome;

  @NotBlank
  @CPF
  @Length(max = 14)
  private String cpf;

  @Length(min = 10, max = 11)
  private String numeroTelefone;

  @NotBlank
  @Email
  @Length(max = 100)
  private String email;

  @NotBlank
  @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Formato de data inválido. Utilize o formato dd/MM/yyyy.")
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private String dataNascimentoString;
}

