package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.PedidoCreateDTO;
import br.com.ufrn.imd.supermarket_api.dtos.PedidoUpdateDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.model.PedidoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    public List<PedidoEntity> buscarPedido() {
        return repository.findAll();
    }

    public PedidoEntity buscarPedido(Long id) {
        Optional<PedidoEntity> pedido = repository.findById(id);
        if(pedido.isEmpty()) {
            return null;
        }
        return pedido.get();
    }

    public PedidoEntity salvarPedido(PedidoCreateDTO pedidoCreateDTO) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedidoCreateDTO, pedidoEntity);

        ClienteEntity cliente = clienteService.buscarCliente(pedidoCreateDTO.cliente_id());
        if (cliente == null) {
            return null;
        }
        pedidoEntity.setCliente(cliente);

        pedidoEntity.ativar();
        return repository.save(pedidoEntity);
    }

    public boolean deleteLogic(Long id) {
        PedidoEntity pedido = buscarPedido(id);
        if(pedido == null) {
            return false;
        }

        pedido.desativar();
        repository.save(pedido);

        return true;
    }

    public PedidoEntity atualizarPedido(Long id, PedidoUpdateDTO pedidoUpdateDTO) {
        PedidoEntity pedido = buscarPedido(id);

        if(pedido == null) {
            return null;
        }

        if (pedidoUpdateDTO.codigo() != null) {
            pedido.setCodigo(pedidoUpdateDTO.codigo());
        }

        if (pedidoUpdateDTO.cliente_id() != null) {
            ClienteEntity cliente = clienteService.buscarCliente(pedidoUpdateDTO.cliente_id());
            pedido.setCliente(cliente);
        }

        return repository.save(pedido);
    }

    public boolean apagarPedido(Long id) {
        if(!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}
