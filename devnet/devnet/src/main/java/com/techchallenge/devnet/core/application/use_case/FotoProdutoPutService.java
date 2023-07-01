package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver_primario.dtos.requisicao.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver_primario.dtos.resposta.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.entrada.IFotoProdutoService;
import com.techchallenge.devnet.core.application.ports.saida.IFotoProdutoRepository;
import com.techchallenge.devnet.core.application.ports.saida.ILocalFotoProdutoArmazemService;
import com.techchallenge.devnet.core.application.ports.saida.IProdutoRepositoryPort;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.adapter.driver_primario.conversores.IMapper;
import com.techchallenge.devnet.core.domain.models.FotoProduto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class FotoProdutoPutService implements IFotoProdutoService.PutService {

  public static String diretorioDeFotos = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\foto_store\\";

  @Autowired
  private IMapper mapper;

  @Autowired
  private IProdutoRepositoryPort.GetRepository produtoGetRepository;

  @Autowired
  private IFotoProdutoRepository.PostRepository fotoProdutoPostRepository;

  @Autowired
  private ILocalFotoProdutoArmazemService localFotoProdutoArmazemService;

  @Transactional
  @Override
  public FotoProdutoDtoResponse inserirFotoNoProduto(final Long id, final FotoProdutoDtoRequest fotoDtoRequest) throws IOException {

    var produto = this.produtoGetRepository.consultarPorId(id)
      .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

    MultipartFile arquivoFoto = fotoDtoRequest.getFoto();

    var fotoProdutoPraSalvar = FotoProduto.builder()
      .nome(this.localFotoProdutoArmazemService.gerarNomeArquivoParaArmazenar(id, fotoDtoRequest.getFoto().getOriginalFilename()))
      .descricao(fotoDtoRequest.getDescricao())
      .tipo(arquivoFoto.getContentType())
      .tamanho(arquivoFoto.getSize())
      .produto(produto)
      .build();

    var fotoProdutoSalvo = this.fotoProdutoPostRepository.salvar(fotoProdutoPraSalvar, arquivoFoto.getInputStream());

    return this.mapper.converterEntidadeParaDtoResponse(fotoProdutoSalvo, FotoProdutoDtoResponse.class);
  }
}

