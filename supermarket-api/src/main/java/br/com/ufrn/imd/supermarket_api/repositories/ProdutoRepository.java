package br.com.ufrn.imd.supermarket_api.repositories;

import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}
