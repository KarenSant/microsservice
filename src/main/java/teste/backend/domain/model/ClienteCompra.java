package teste.backend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ClienteCompra {

    private String nome;
    private String cpf;
    private List<CompraItem> compras;

}
