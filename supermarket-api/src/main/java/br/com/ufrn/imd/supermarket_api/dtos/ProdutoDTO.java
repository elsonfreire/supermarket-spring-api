package br.com.ufrn.imd.supermarket_api.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ProdutoDTO(
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
