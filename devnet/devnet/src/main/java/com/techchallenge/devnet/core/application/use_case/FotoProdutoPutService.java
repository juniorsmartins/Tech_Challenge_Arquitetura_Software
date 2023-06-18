package com.techchallenge.devnet.core.application.use_case;

import com.techchallenge.devnet.adapter.driver.dtos.request.FotoProdutoDtoRequest;
import com.techchallenge.devnet.core.application.ports.IProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Service
public class FotoProdutoPutService implements IFotoProdutoService.AtualizarService {

  public static String diretorioDeFotos = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\foto_store\\";

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Override
  public void inserirFotoNoProduto(final Long id, final FotoProdutoDtoRequest fotoProdutoDtoRequest) {

//    this.produtoGetRepository.consultarPorId(id)
//      .map(produto -> {

        var nomeArquivo = id + "_" + fotoProdutoDtoRequest.getFoto().getOriginalFilename().toLowerCase().trim();
        var caminhoFoto = Path.of(diretorioDeFotos, nomeArquivo);

        try {
          fotoProdutoDtoRequest.getFoto().transferTo(caminhoFoto);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }

//        return produto;
//      })
//      .orElseThrow();
  }
}

