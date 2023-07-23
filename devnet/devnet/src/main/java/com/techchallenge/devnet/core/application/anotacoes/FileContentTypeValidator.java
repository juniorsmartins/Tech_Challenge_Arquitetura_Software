package com.techchallenge.devnet.core.application.anotacoes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

  private List<String> tiposDeArquivoPermitido;

  @Override
  public void initialize(FileContentType constraint) {
    this.tiposDeArquivoPermitido = Arrays.asList(constraint.allowed());
  }

  @Override
  public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
    return multipartFile == null || this.tiposDeArquivoPermitido.contains(multipartFile.getContentType());
  }
}

