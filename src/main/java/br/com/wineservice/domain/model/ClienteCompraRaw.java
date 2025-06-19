package br.com.wineservice.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClienteCompraRaw {
    private String nome;
    private String cpf;
    private List<CompraRaw> compras;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompraRaw {
        private int codigo;
        private int quantidade;
    }
}

