package br.com.ufrn.imd.supermarket_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class PedidoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean ativo;

    private String codigo;

    @ManyToMany()
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedido_fk"),
            inverseJoinColumns = @JoinColumn(name = "produto_fk")
    )
    private List<ProdutoEntity> produtos;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void addProduto(ProdutoEntity produto) {
        this.produtos.add(produto);
    }
}
