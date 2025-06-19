package br.com.wineservice.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProdutoDTO {
    private String codigo;
    private String nome;
    private String tipoVinho;
    private String safra;
    private double preco;
    private int anoCompra;
}
