package br.com.wineservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cliente_compras")
public class ClienteCompra {
    private String nome;

    @Id
    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cliente_cpf")
    private List<CompraItem> compras;

    public List<CompraItem> getCompras() {
        return compras;
    }

}
