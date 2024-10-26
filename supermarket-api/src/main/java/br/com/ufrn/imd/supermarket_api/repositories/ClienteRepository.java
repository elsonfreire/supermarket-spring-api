package br.com.ufrn.imd.supermarket_api.repositories;

import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
