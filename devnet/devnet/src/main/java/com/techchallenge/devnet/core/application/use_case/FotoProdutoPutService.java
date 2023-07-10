package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepositoryPort;
import com.techchallenge.devnet.core.application.ports.entrada.IArmazemFotoProdutoServicePort;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
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
public class FotoProdutoPutService implements IFotoProdutoServicePort.PutService {

  public static String diretorioDeFotos = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\foto_store\\";

  @Autowired
  private IProdutoRepositoryPort.GetRepository produtoGetRepository;

  @Autowired
  private IFotoProdutoRepositoryPort.PostRepository fotoProdutoPostRepository;

  @Autowired
  private IArmazemFotoProdutoServicePort armazemFotoProdutoService;

  @Override
  public FotoProdutoModel inserirFotoNoProduto(final Long id, final FotoProdutoArquivo fotoProdutoArquivo) throws IOException {

    var produtoModel = this.produtoGetRepository.consultarPorId(id)
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

