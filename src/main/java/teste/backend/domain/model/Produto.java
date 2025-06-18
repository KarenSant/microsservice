package teste.backend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Produto {
    private String codigo;
    private String nome;
    private String tipoVinho;
    private String safra;
    private double preco;
    private int anoCompra;
}
