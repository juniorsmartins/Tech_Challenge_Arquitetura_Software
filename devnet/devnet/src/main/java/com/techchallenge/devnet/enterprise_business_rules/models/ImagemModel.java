package com.techchallenge.devnet.enterprise_business_rules.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ImagemModel implements Serializable {

  private static final long serialVersionUID = 1L;

  private InputStreamResource imagem;

  private MediaType mediaTypeFoto;
}

