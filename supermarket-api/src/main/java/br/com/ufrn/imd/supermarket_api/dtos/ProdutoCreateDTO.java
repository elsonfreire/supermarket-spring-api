package br.com.ufrn.imd.supermarket_api.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProdutoCreateDTO(
    Long id,
    @NotBlank
    String nomeProduto,
    @NotBlank
    String marca,
    @NotBlank
    String dataFabricacao,
    @NotBlank
    String dataValidade,
    @NotBlank
    String genero,
    @NotBlank
    String lote)
    {}
