package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.ProdutoCreateDTO;
import br.com.ufrn.imd.supermarket_api.dtos.ProdutoUpdateDTO;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    public ProdutoEntity salvarProduto(ProdutoCreateDTO produtoCreateDTO) {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        BeanUtils.copyProperties(produtoCreateDTO, produtoEntity);

        //Converte o gênero e a data de nascimento de string para Enum e LocalDate, respectivamente
        if (produtoCreateDTO.genero() != null) {
            produtoEntity.setGenero(ProdutoEntity.Genero.valueOf(produtoCreateDTO.genero()));
        }

        if (produtoCreateDTO.dataFabricacao() != null) {
            produtoEntity.setDataFabricacao(LocalDate.parse(produtoCreateDTO.dataFabricacao()));
        }

        if (produtoCreateDTO.dataValidade() != null) {
            produtoEntity.setDataValidade(LocalDate.parse(produtoCreateDTO.dataValidade()));
        }

        return repository.save(produtoEntity);
    }

    public ProdutoEntity atualizarProduto(Long id, ProdutoUpdateDTO produtoUpdateDTO) {
        Optional<ProdutoEntity> produto = repository.findById(id);

        if(produto.isEmpty()) {
            return null;
        }

        ProdutoEntity produtoEntity = produto.get();

        // Campos opcionais
        if (produtoUpdateDTO.nomeProduto() != null) {
            produtoEntity.setNomeProduto(produtoUpdateDTO.nomeProduto());
        }

        if (produtoUpdateDTO.marca() != null) {
            produtoEntity.setMarca(produtoUpdateDTO.marca());
        }

        if (produtoUpdateDTO.dataFabricacao() != null) {
            produtoEntity.setDataFabricacao(LocalDate.parse(produtoUpdateDTO.dataFabricacao()));
        }

        if(produtoUpdateDTO.dataValidade() != null) {
            produtoEntity.setDataValidade(LocalDate.parse(produtoUpdateDTO.dataValidade()));
        }

        if (produtoUpdateDTO.genero() != null) {
            produtoEntity.setGenero(ProdutoEntity.Genero.valueOf(produtoUpdateDTO.genero()));
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
