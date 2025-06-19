package br.com.wineservice.adapter.in.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClienteCompraDTO {
    private String nome;
    private String cpf;
    private List<CompraItemDTO> compras;
}