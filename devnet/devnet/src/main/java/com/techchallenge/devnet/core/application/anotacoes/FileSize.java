package com.techchallenge.devnet.core.application.anotacoes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSize {

  String message() default "Tamanho do arquivo inválido. Excede o limite permitido.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String max();
}

