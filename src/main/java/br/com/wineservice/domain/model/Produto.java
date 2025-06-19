package br.com.wineservice.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    private String codigo;
    private String safra;
    private double preco;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("tipo_vinho")
    private String tipoVinho;

    @JsonProperty("ano_compra")
    private int anoCompra;
}
