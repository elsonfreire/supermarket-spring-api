package br.com.ufrn.imd.supermarket_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nomeProduto;
    String marca;
    LocalDate dataFabricacao;
    LocalDate dataValidade;
    Genero genero;
    String lote;

    enum Genero {
        COSMETICO, ALIMENTICIO, HIGIENE_PESSOAL, LIMPEZA
    };
}
