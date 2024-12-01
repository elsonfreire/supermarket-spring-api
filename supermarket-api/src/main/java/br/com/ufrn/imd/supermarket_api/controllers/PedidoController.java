package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.PedidoDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.model.PedidoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.PedidoRepository;
import org.springframework.beans.BeanUtils;
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
    private PedidoRepository repository;

    @GetMapping
    public ResponseEntity<List<PedidoEntity>> getAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<PedidoEntity> pedido = repository.findById(id);
        if(pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.ok().body(pedido.get())
    }

    @PostMapping
    public ResponseEntity<PedidoEntity> postPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedidoDTO, pedidoEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(pedidoEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        Optional<PedidoEntity> pedido = repository.findById(id);

        if(pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        PedidoEntity pedidoEntity = pedido.get();

        if (pedidoDTO.codigo() != null) {
            pedidoEntity.setCodigo(pedidoDTO.codigo());
        }

        if (pedidoDTO.produtos() != null) {
            pedidoEntity.setProdutos(pedidoDTO.produtos());
        }

        if (pedidoDTO.cliente() != null) {
            pedidoEntity.setCliente(pedidoDTO.cliente());
        }

        return ResponseEntity.ok().body(repository.save(pedidoEntity));
    }
}
