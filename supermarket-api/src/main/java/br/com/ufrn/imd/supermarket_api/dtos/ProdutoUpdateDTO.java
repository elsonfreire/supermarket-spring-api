package br.com.ufrn.imd.supermarket_api.dtos;

public record ProdutoUpdateDTO(
        String nomeProduto,
        String marca,
        String dataFabricacao,
        String dataValidade,
        String genero,
        String lote
)
{}
