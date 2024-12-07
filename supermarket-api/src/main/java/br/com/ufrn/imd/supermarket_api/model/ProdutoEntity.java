package br.com.ufrn.imd.supermarket_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class ProdutoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeProduto;
    private String marca;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String lote;

    public enum Genero {
        COSMETICO,
        ALIMENTICIO,
        HIGIENE_PESSOAL,
        LIMPEZA
    };

    @ManyToMany(mappedBy = "produtos")
    private List<PedidoEntity> pedidos;
}
