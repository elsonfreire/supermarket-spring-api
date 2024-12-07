package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.PedidoCreateDTO;
import br.com.ufrn.imd.supermarket_api.dtos.PedidoUpdateDTO;
import br.com.ufrn.imd.supermarket_api.model.PedidoEntity;
import br.com.ufrn.imd.supermarket_api.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoEntity>> getAll() {
        return ResponseEntity.ok().body(pedidoService.buscarPedido());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        PedidoEntity pedido = pedidoService.buscarPedido(id);
        if(pedido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
    public ResponseEntity<Object> postPedido(@RequestBody PedidoCreateDTO pedidoCreateDTO) {
        var pedido = pedidoService.salvarPedido(pedidoCreateDTO);

        if(pedido == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível criar o pedido");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putPedido(@PathVariable Long id, @RequestBody PedidoUpdateDTO pedidoUpdateDTO) {
        PedidoEntity pedido = pedidoService.atualizarPedido(id, pedidoUpdateDTO);

        if(pedido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }


        return ResponseEntity.ok().body(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable Long id) {
        boolean pedidoExiste = pedidoService.apagarPedido(id);

        if(!pedidoExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.ok().body("Pedido deletado com sucesso");
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Object> deleteLogic(@PathVariable Long id) {
        boolean pedidoExiste = pedidoService.deleteLogic(id);

        if(!pedidoExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/produtos/{produto_id}")
    public ResponseEntity<Object> adicionarProduto(@PathVariable Long id, @PathVariable Long produto_id) {
        try{
            PedidoEntity pedido = pedidoService.adicionarProduto(id, produto_id);
            return ResponseEntity.ok().body(pedido);
        } catch(Error error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}/produtos/{produto_id}")
    public ResponseEntity<Object> removerProduto(@PathVariable Long id, @PathVariable Long produto_id) {
        try{
            PedidoEntity pedido = pedidoService.removerProduto(id, produto_id);
            return ResponseEntity.ok().body(pedido);
        } catch(Error error) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }
}
