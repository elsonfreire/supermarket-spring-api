package br.com.ufrn.imd.supermarket_api.dtos;

public record ClienteDTO (
    Long id,
    String nome,
    String cpf,
    String genero,
    String dataNascimento)
    {}
