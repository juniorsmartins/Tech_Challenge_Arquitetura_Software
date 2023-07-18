package com.techchallenge.devnet.core.domain.base.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "mercado-pago")
public class MercadoPagoConfig {

  private String clientId;
  private String clientSecret;
}

