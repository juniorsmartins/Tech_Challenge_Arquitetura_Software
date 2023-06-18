package com.techchallenge.devnet.adapter.driver.dtos.resposta;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoDtoResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String qrCode;
}

