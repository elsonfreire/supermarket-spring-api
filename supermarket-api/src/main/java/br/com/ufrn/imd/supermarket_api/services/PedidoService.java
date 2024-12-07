package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.PedidoCreateDTO;
import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.model.PedidoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PedidoService {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    public List<PedidoEntity> buscarPedidos() {
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

        ClienteEntity cliente = clienteService.buscarCliente(pedidoCreateDTO.id());
        if (cliente == null) {
            return null;
        }
        pedidoEntity.setCliente(cliente);

        pedidoEntity.ativar();
        return repository.save(pedidoEntity);
    }

    public boolean deleteLogic(Long id) {
        Optional<PedidoEntity> pedido = repository.findById(id);
        if(pedido.isEmpty()) {
            return false;
        }

        PedidoEntity pedidoEntity = pedido.get();
        pedidoEntity.desativar();
        return true;
    }

//    public PedidoEntity atualizarPedido() {
//
//    }
}
