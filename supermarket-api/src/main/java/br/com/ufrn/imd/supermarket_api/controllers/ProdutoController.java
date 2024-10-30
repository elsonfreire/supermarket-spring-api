package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.ProdutoDTO;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> getAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<ProdutoEntity> produto = repository.findById(id);
        if(produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.ok().body(produto.get());
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> postProduto(@RequestBody ProdutoDTO produtoDTO) {
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

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produtoEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        Optional<ProdutoEntity> produto = repository.findById(id);

        if(produto.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
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

        return ResponseEntity.ok().body(repository.save(produtoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
