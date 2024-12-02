package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.ClienteCreateDTO;
import br.com.ufrn.imd.supermarket_api.dtos.ClienteUpdateDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.repositories.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

    public ClienteEntity salvarCliente(ClienteCreateDTO clienteCreateDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(clienteCreateDTO, clienteEntity);

        if (clienteCreateDTO.genero() != null) {
            clienteEntity.setGenero(ClienteEntity.Genero.valueOf(clienteCreateDTO.genero()));
        }

        if (clienteCreateDTO.dataNascimento() != null) {
            clienteEntity.setDataNascimento(LocalDate.parse(clienteCreateDTO.dataNascimento()));
        }

        return repository.save(clienteEntity);
    }

    public ClienteEntity atualizarCliente(Long id, ClienteUpdateDTO clienteUpdateDTO) {
        Optional<ClienteEntity> cliente = repository.findById(id);

        if(cliente.isEmpty()) {
            return null;
        }
        ClienteEntity clienteEntity = cliente.get();

        if (clienteUpdateDTO.nome() != null) {
            clienteEntity.setNome(clienteUpdateDTO.nome());
        }

        if (clienteUpdateDTO.cpf() != null) {
            clienteEntity.setCpf(clienteUpdateDTO.cpf());
        }

        if (clienteUpdateDTO.genero() != null) {
            clienteEntity.setGenero(ClienteEntity.Genero.valueOf(clienteUpdateDTO.genero()));
        }

        if (clienteUpdateDTO.dataNascimento() != null) {
            clienteEntity.setDataNascimento(LocalDate.parse(clienteUpdateDTO.dataNascimento()));
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
