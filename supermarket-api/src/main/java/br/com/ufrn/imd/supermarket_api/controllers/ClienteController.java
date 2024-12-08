package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.ClienteCreateDTO;
import br.com.ufrn.imd.supermarket_api.dtos.ClienteUpdateDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.ok().body(clienteService.buscarCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        var cliente = clienteService.buscarCliente(id);
        if(cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> postCliente(@RequestBody ClienteCreateDTO clienteCreateDTO) {
        //Converte o gênero e a data de nascimento de string para Enum e LocalDate, respectivamente
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvarCliente(clienteCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putCliente(@PathVariable Long id, @RequestBody ClienteUpdateDTO clienteUpdateDTO) {
        ClienteEntity cliente = clienteService.atualizarCliente(id, clienteUpdateDTO);
        if(cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        boolean clienteExiste = clienteService.apagarCliente(id);

        if(!clienteExiste) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.noContent().build();
    }
}
