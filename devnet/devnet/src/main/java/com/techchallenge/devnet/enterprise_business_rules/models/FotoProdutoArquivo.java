package com.techchallenge.devnet.enterprise_business_rules.models;

import com.techchallenge.devnet.application_business_rules.anotacoes.FileContentType;
import com.techchallenge.devnet.application_business_rules.anotacoes.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class FotoProdutoArquivo implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  @FileSize(max = "2MB")
  @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  private MultipartFile foto;

  @NotBlank
  private String descricao;
}

