package br.com.ufrn.imd.supermarket_api.dtos;

import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteUpdateDTO(
        String nome,
        @CPF(message = "Digite um CPF válido")
        String cpf,
        String genero,
        @Past
        String dataNascimento
) {
}
