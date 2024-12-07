package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.ProdutoCreateDTO;
import br.com.ufrn.imd.supermarket_api.dtos.ProdutoUpdateDTO;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import br.com.ufrn.imd.supermarket_api.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ProdutoEntity> postProduto(@RequestBody ProdutoCreateDTO produtoCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produtoCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putProduto(@PathVariable Long id, @RequestBody ProdutoUpdateDTO produtoUpdateDTO) {
        var produto = produtoService.atualizarProduto(id, produtoUpdateDTO);
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
