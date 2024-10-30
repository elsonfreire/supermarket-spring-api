package br.com.ufrn.imd.supermarket_api.dtos;

import java.time.LocalDate;

public record ProdutoDTO(
    Long id,
    String nomeProduto,
    String marca,
    String dataFabricacao,
    String dataValidade,
    String genero,
    String lote)
    {}
