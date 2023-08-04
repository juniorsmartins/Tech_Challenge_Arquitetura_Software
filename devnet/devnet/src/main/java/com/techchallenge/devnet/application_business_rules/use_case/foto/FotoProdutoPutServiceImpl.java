package com.techchallenge.devnet.application_business_rules.use_case.foto;

import com.techchallenge.devnet.application_business_rules.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.application_business_rules.ports.entrada.foto.FotoProdutoAtualizarServicePort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.foto.FotoProdutoArmazemPort;
import com.techchallenge.devnet.application_business_rules.ports.saida.produto.ProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoArquivo;
import com.techchallenge.devnet.enterprise_business_rules.models.FotoProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class FotoProdutoPutServiceImpl implements FotoProdutoAtualizarServicePort {

  private final ProdutoConsultarPorIdRepositoryPort produtoConsultarPorIdRepository;

  private final FotoProdutoSalvarRepositoryPort fotoProdutoPostRepository;

  private final FotoProdutoArmazemPort armazemFotoProdutoService;

  public FotoProdutoPutServiceImpl(ProdutoConsultarPorIdRepositoryPort produtoConsultarPorIdRepository,
                                   FotoProdutoSalvarRepositoryPort fotoProdutoPostRepository,
                                   FotoProdutoArmazemPort armazemFotoProdutoService) {
    this.produtoConsultarPorIdRepository = produtoConsultarPorIdRepository;
    this.fotoProdutoPostRepository = fotoProdutoPostRepository;
    this.armazemFotoProdutoService = armazemFotoProdutoService;
  }

  @Transactional
  @Override
  public FotoProdutoModel inserirFotoNoProduto(final Long id, final FotoProdutoArquivo fotoProdutoArquivo) throws IOException {

    var produtoModel = this.produtoConsultarPorIdRepository.consultarPorId(id)
      .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

    MultipartFile arquivoFoto = fotoProdutoArquivo.getFoto();

    var fotoProdutoModelPraSalvar = FotoProdutoModel.builder()
      .nome(this.armazemFotoProdutoService.gerarNomeArquivoParaArmazenar(id, fotoProdutoArquivo.getFoto().getOriginalFilename()))
      .descricao(fotoProdutoArquivo.getDescricao())
      .tipo(arquivoFoto.getContentType())
      .tamanho(arquivoFoto.getSize())
      .produto(produtoModel)
      .build();

    var fotoProdutoModelSalvo = this.fotoProdutoPostRepository.salvar(fotoProdutoModelPraSalvar, arquivoFoto.getInputStream());
    return fotoProdutoModelSalvo;
  }
}

