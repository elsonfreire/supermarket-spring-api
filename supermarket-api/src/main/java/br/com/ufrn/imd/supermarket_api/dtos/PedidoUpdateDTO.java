package br.com.ufrn.imd.supermarket_api.dtos;

public record PedidoUpdateDTO(
        String codigo,
        Long id_cliente
)
{}