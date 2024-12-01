package br.com.ufrn.imd.supermarket_api.services;

import br.com.ufrn.imd.supermarket_api.dtos.PedidoDTO;
import br.com.ufrn.imd.supermarket_api.model.PedidoEntity;
import br.com.ufrn.imd.supermarket_api.repositories.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class PedidoService {
    @Autowired
    private PedidoRepository repository;

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

    public PedidoEntity salvarPedido(PedidoDTO pedidoDTO) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        BeanUtils.copyProperties(pedidoDTO, pedidoEntity);

        return repository.save(pedidoEntity);
    }

    public PedidoEntity atualizarPedido() {

    }
}
