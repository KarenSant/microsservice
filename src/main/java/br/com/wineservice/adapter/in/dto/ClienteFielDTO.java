package br.com.wineservice.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteFielDTO {
    private String nome;
    private String cpf;
    private int quantidadeCompras;
    private double valorTotalCompras;
}
