package br.com.ufrn.imd.supermarket_api.dtos;

import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;

import java.util.List;

public record PedidoDTO (
    Long id,
    String codigo,
    List<ProdutoEntity> produtos,
    ClienteEntity cliente)
{}
