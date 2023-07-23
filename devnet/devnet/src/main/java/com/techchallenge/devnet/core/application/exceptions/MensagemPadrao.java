package com.techchallenge.devnet.core.application.exceptions;

public final class MensagemPadrao {

  public static final String CPF_JA_CADASTRADO = "O CPF %s está cadastrado no sistema. Não pode haver duplicação. E seu cadastro pode ser recuperado na aba Ajuda.";

  public static final String CPF_NAO_ENCONTRADO = "O Cliente, com CPF %s, não foi encontrado.";

  public static final String CPF_INVALIDO = "O Cadastro de Pessoa Física (CPF), de número %s, é inválido.";

  public static final String EMAIL_INVALIDO = "O Email, %s, é inválido.";

  public static final String TELEFONE_INVALIDO = "O Telefone, %s, é inválido.";

  public static final String DATA_NASCIMENTO_INVALIDA = "A data de nascimento, %s, é inválida.";

  public static final String MEDIA_NAO_SUPORTADA = "O recurso não suporta esse tipo de midia.";

  public static final String CLIENTE_NAO_ENCONTRADO = "O Cliente, com id %s, não foi encontrado!";

  public static final String PRODUTO_NAO_ENCONTRADO = "O Produto, com id %s, não foi encontrado!";

  public static final String PEDIDO_NAO_ENCONTRADO = "O Pedido, com id %s, não foi encontrado!";

  public static final String CANCELAMENTO_BLOQUEADO = "O Pedido, com id %s, não pode ser cancelado por estar no status: %s.";

  public static final String FOTO_PRODUTO_NAO_ENCONTRADO = "Não existe cadastro de foto do produto com id %d.";

  public static final String FOTO_PRODUTO_NAO_ARMAZENADO = "Não foi possível armazenar arquivo de foto.";

  public static final String FOTO_PRODUTO_NAO_APAGADO_DO_ARMAZENAMENTO = "Não foi possível deletar arquivo de foto do armazenamento.";

  public static final String FOTO_PRODUTO_NAO_RECUPERADO_DO_ARMAZENAMENTO = "Não foi possível recuperar arquivo de foto do armazenamento.";

  public static final String QRCODE_NAO_RECUPERADO_DO_ARMAZENAMENTO = "Não foi possível recuperar QrCode do local de armazenamento.";

  public static final String PAGAMENTO_NAO_ENCONTRADO = "O Pagamento, com id %s, não foi encontrado.";

  public static final String PAGAMENTO_BLOQUEADO = "O Pedido, com id %s, não pode ter confirmação de pagamento por estar no status: %s.";

  public static final String PEDIDO_BLOQUEADO_PARA_ATUALIZAR = "O Pedido, com id %s, não pode ser atualizado por estar no status: %s.";

  public static final String PEDIDO_BLOQUEADO_PARA_PRONTO = "O Pedido, com id %s, não pode ser alterado para PRONTO por estar no status: %s.";

  public static final String PEDIDO_BLOQUEADO_PARA_FINALIZADO = "O Pedido, com id %s, não pode ser alterado para FINALIZADO por estar no status: %s.";

  public static final String IDS_INCOMPATIVEIS = "Os dois IDs enviados, %s e %s, são incompatíveis.";

  public static final String EMAIL_NAO_ENVIADO_POR_EXCECAO = "Email não enviado por exceção!";

  public static final String BLOQUEADO_POR_USO = "O recurso, com id %s, está em uso e não pode ser apagado.";
}

