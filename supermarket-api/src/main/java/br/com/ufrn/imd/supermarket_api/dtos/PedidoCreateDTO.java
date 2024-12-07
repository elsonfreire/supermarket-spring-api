package br.com.ufrn.imd.supermarket_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record PedidoCreateDTO(
    Long id,
    @NotBlank
    String codigo,
    @NotBlank
    Long cliente_id)
{}
