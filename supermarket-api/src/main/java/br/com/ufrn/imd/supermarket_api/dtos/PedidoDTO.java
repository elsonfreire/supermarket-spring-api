package br.com.ufrn.imd.supermarket_api.dtos;

import br.com.ufrn.imd.supermarket_api.model.ClienteEntity;
import br.com.ufrn.imd.supermarket_api.model.ProdutoEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record PedidoDTO (
    Long id,
    @NotBlank
    String codigo,
    @NotBlank
    Long id_cliente)
{}
