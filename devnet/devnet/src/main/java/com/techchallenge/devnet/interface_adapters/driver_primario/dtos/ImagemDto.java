package com.techchallenge.devnet.interface_adapters.driver_primario.dtos;

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
public final class ImagemDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private InputStreamResource imagem;

  private MediaType mediaTypeFoto;
}

