package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.FotoProdutoDtoRequest;
import com.techchallenge.devnet.adapter.driver.dtos.response.FotoProdutoDtoResponse;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import com.techchallenge.devnet.core.domain.base.exceptions.http_404.ProdutoNaoEncontradoException;
import com.techchallenge.devnet.core.domain.base.mappers.IMapper;
import com.techchallenge.devnet.core.domain.entities.FotoProduto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FotoProdutoPutService implements IFotoProdutoService.AtualizarService {

  public static String diretorioDeFotos = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\foto_store\\";

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Autowired
  private IProdutoRepository.PostRepository produtoPostRepository;

  @Autowired
  private IMapper mapper;

  @Transactional
  @Override
  public FotoProdutoDtoResponse inserirFotoNoProduto(final Long id, final FotoProdutoDtoRequest fotoDtoRequest) {

    var produto = this.produtoGetRepository.consultarPorId(id)
      .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

    MultipartFile arquivoFoto = fotoDtoRequest.getFoto();
    var fotoProdutoPraSalvar = FotoProduto.builder()
      .nome(arquivoFoto.getOriginalFilename().toLowerCase().trim())
      .descricao(fotoDtoRequest.getDescricao())
      .tipo(arquivoFoto.getContentType())
      .tamanho(arquivoFoto.getSize())
      .produto(produto)
      .build();
    var fotoProduto = this.produtoPostRepository.save(fotoProdutoPraSalvar);

    return this.mapper.converterEntidadeParaDtoResponse(fotoProduto, FotoProdutoDtoResponse.class);


//        var nomeArquivo = id + "_" + fotoDtoRequest.getFoto().getOriginalFilename().toLowerCase().trim();
//        var caminhoFoto = Path.of(diretorioDeFotos, nomeArquivo);
//
//        try {
//          fotoDtoRequest.getFoto().transferTo(caminhoFoto);
//        } catch (IOException e) {
//          throw new RuntimeException(e);
//        }


  }
}

