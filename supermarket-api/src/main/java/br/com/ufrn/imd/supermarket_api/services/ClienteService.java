package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.ClienteDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<ClienteEntity> buscarClientes() {
        return repository.findAll();
    }

    public ClienteEntity buscarCliente(Long id) {
        Optional<ClienteEntity> cliente = repository.findById(id);
        if(cliente.isEmpty()) {
            return null;
        }
        return cliente.get();
    }

    public ClienteEntity salvarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, clienteEntity);

        if (clienteDTO.genero() != null) {
            clienteEntity.setGenero(ClienteEntity.Genero.valueOf(clienteDTO.genero()));
        }

        if (clienteDTO.dataNascimento() != null) {
            clienteEntity.setDataNascimento(LocalDate.parse(clienteDTO.dataNascimento()));
        }

        return repository.save(clienteEntity);
    }

    public ClienteEntity atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<ClienteEntity> cliente = repository.findById(id);

        if(cliente.isEmpty()) {
            return null;
        }
        ClienteEntity clienteEntity = cliente.get();

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

        return repository.save(clienteEntity);
    }

    public boolean apagarCliente(Long id) {
        if(!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
