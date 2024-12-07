package br.com.ufrn.imd.supermarket_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "clientes")
public class ClienteEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private LocalDate dataNascimento;

    public enum Genero {
        MASCULINO,
        FEMININO,
        OUTRO
    }

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<PedidoEntity> pedidos;
}
