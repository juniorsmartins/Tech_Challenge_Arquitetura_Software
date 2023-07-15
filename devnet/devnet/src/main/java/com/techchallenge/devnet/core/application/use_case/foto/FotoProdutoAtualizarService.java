package com.techchallenge.devnet.core.application.use_case.foto;

import com.techchallenge.devnet.core.application.ports.saida.foto.IArmazemFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.entrada.foto.IFotoProdutoAtualizarServicePort;
import com.techchallenge.devnet.core.application.ports.saida.foto.IFotoProdutoSalvarRepositoryPort;
import com.techchallenge.devnet.core.application.ports.saida.produto.IProdutoConsultarPorIdRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.models.FotoProdutoArquivo;
import com.techchallenge.devnet.core.domain.models.FotoProdutoModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class FotoProdutoAtualizarService implements IFotoProdutoAtualizarServicePort {

  public static String diretorioDeFotos = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\foto_store\\";

  @Autowired
  private IProdutoConsultarPorIdRepositoryPort produtoConsultarPorIdRepository;

  @Autowired
  private IFotoProdutoSalvarRepositoryPort fotoProdutoPostRepository;

  @Autowired
  private IArmazemFotoProdutoRepositoryPort armazemFotoProdutoService;

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
