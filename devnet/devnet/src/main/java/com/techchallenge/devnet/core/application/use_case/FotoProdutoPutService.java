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

  public static String fotoStore = "D:\\AreaEstudo\\PosTech-Fiap-Alura\\ArquiteturaDeSoftware\\TechChallenge-Soat\\devnet\\devnet\\foto_store\\";

  @Autowired
  private IProdutoRepository.GetRepository produtoGetRepository;

  @Override
  public void inserirFotoNoProduto(final Long id, FotoProdutoDtoRequest fotoProdutoDtoRequest) {

//    this.produtoGetRepository.consultarPorId(id)
//      .map(produto -> {

        var nomeArquivo = id + "_" + fotoProdutoDtoRequest.getFoto().getOriginalFilename();
        var caminhoFoto = Path.of(fotoStore, nomeArquivo);

        System.out.println(caminhoFoto);
        System.out.println(fotoProdutoDtoRequest.getFoto().getContentType());

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

