package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.ProdutoDTO;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ProdutoRepository;
import br.com.ufrn.imd.supermarket_api.services.ProdutoService;
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
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoEntity>> getAll() {
        return ResponseEntity.ok().body(produtoService.buscarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        var produto = produtoService.buscarProduto(id);
        if(produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoEntity> postProduto(@RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produtoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        var produto = produtoService.atualizarProduto(id, produtoDTO);
        if(produto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id) {
        boolean produtoExiste = produtoService.apagarProduto(id);
        if(!produtoExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.noContent().build();
    }
}
