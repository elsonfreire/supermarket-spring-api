package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.ProdutoDTO;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoEntity> buscarProdutos() {
        return repository.findAll();
    }

    public ProdutoEntity buscarProduto(Long id) {
        Optional<ProdutoEntity> produto = repository.findById(id);
        if(produto.isEmpty()) {
            return null;
        }
        return produto.get();
    }

    public ProdutoEntity salvarProduto(ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoDTO, produtoEntity);

        //Converte o gênero e a data de nascimento de string para Enum e LocalDate, respectivamente
        if (produtoDTO.genero() != null) {
            produtoEntity.setGenero(ProdutoEntity.Genero.valueOf(produtoDTO.genero()));
        }

        if (produtoDTO.dataFabricacao() != null) {
            produtoEntity.setDataFabricacao(LocalDate.parse(produtoDTO.dataFabricacao()));
        }

        if (produtoDTO.dataValidade() != null) {
            produtoEntity.setDataValidade(LocalDate.parse(produtoDTO.dataValidade()));
        }

        return repository.save(produtoEntity);
    }

    public ProdutoEntity atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Optional<ProdutoEntity> produto = repository.findById(id);

        if(produto.isEmpty()) {
            return null;
        }

        ProdutoEntity produtoEntity = produto.get();

        // Campos opcionais
        if (produtoDTO.nomeProduto() != null) {
            produtoEntity.setNomeProduto(produtoDTO.nomeProduto());
        }

        if (produtoDTO.marca() != null) {
            produtoEntity.setMarca(produtoDTO.marca());
        }

        if (produtoDTO.dataFabricacao() != null) {
            produtoEntity.setDataFabricacao(LocalDate.parse(produtoDTO.dataFabricacao()));
        }

        if(produtoDTO.dataValidade() != null) {
            produtoEntity.setDataValidade(LocalDate.parse(produtoDTO.dataValidade()));
        }

        if (produtoDTO.genero() != null) {
            produtoEntity.setGenero(ProdutoEntity.Genero.valueOf(produtoDTO.genero()));
        }

        return repository.save(produtoEntity);
    }

    public boolean apagarProduto(Long id) {
        if(!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
