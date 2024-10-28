package br.com.ufrn.imd.supermarket_api.controllers;

import br.com.ufrn.imd.supermarket_api.dtos.ClienteDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public ResponseEntity<List<ClienteEntity>> getAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        Optional<ClienteEntity> cliente = repository.findById(id);
        if(cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.ok().body(cliente.get());
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> postCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, clienteEntity);

        //Converte o gênero e a data de nascimento de string para Enum e LocalDate, respectivamente
        if (clienteDTO.genero() != null) {
            clienteEntity.setGenero(ClienteEntity.Genero.valueOf(clienteDTO.genero()));
        }

        if (clienteDTO.dataNascimento() != null) {
            clienteEntity.setDataNascimento(LocalDate.parse(clienteDTO.dataNascimento()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(clienteEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Optional<ClienteEntity> cliente = repository.findById(id);

        if(cliente.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        ClienteEntity clienteEntity = cliente.get();

        // Campos opcionais
        if (clienteDTO.nome() != null) {
            clienteEntity.setNome(clienteDTO.nome());
        }

        if (clienteDTO.cpf() != null) {
            clienteEntity.setCpf(clienteDTO.cpf());
        }

        if (clienteDTO.genero() != null) {
            clienteEntity.setGenero(ClienteEntity.Genero.valueOf(clienteDTO.genero()));
        }

        if (clienteDTO.dataNascimento() != null) {
            clienteEntity.setDataNascimento(LocalDate.parse(clienteDTO.dataNascimento()));
        }

        return ResponseEntity.ok().body(repository.save(clienteEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
