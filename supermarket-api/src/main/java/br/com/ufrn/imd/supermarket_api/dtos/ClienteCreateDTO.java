package br.com.ufrn.imd.supermarket_api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteCreateDTO(
    Long id,
    @NotBlank
    String nome,
    @NotBlank
    @CPF(message = "Digite um CPF válido")
    String cpf,
    String genero,
    @Past
    String dataNascimento)
    {}
