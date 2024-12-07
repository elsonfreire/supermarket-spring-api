package br.com.ufrn.imd.supermarket_api.repositories;

import br.com.ufrn.imd.supermarket_api.model.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findByAtivo(boolean ativo);
}
