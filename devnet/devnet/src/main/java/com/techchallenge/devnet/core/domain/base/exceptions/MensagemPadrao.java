package com.techchallenge.devnet.core.domain.base.exceptions;

public final class MensagemPadrao {

  public static final String CPF_JA_CADASTRADO = "O CPF %s está cadastrado no sistema. Não pode haver duplicação. E seu cadastro pode ser recuperado na aba Ajuda.";

  public static final String CPF_NAO_ENCONTRADO = "O Cliente, com CPF %s, não foi encontrado.";

  public static final String MIDIA_NAO_SUPORTADA = "A API suporta mídia do tipo Json. Não possui suporte para outros tipos de mídia.";

  public static final String CLIENTE_NAO_ENCONTRADO = "O Cliente, com id %s, não foi encontrado!";

  public static final String PRODUTO_NAO_ENCONTRADO = "O Produto, com id %s, não foi encontrado!";

  public static final String PEDIDO_NAO_ENCONTRADO = "O Pedido, com id %s, não foi encontrado!";

  public static final String FOTO_PRODUTO_NAO_ENCONTRADO = "Não existe cadastro de foto do produto com id %d.";

  public static final String FOTO_PRODUTO_NAO_ARMAZENADO = "Não foi possível armazenar arquivo de foto.";

  public static final String FOTO_PRODUTO_NAO_APAGADO_DO_ARMAZENAMENTO = "Não foi possível deletar arquivo de foto do armazenamento.";

  public static final String FOTO_PRODUTO_NAO_RECUPERADO_DO_ARMAZENAMENTO = "Não foi possível recuperar arquivo de foto do armazenamento.";

  public static final String QRCODE_NAO_RECUPERADO_DO_ARMAZENAMENTO = "Não foi possível recuperar QrCode do local de armazenamento.";
}

