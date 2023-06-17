package com.techchallenge.devnet.adapter.driver.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class FotoProdutoDtoRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private MultipartFile foto;

  @NotBlank
  private String descricao;
}

