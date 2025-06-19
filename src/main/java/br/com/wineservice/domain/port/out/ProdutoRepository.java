package br.com.wineservice.domain.port.out;

import br.com.wineservice.domain.model.ClienteCompra;
import br.com.wineservice.domain.model.Produto;

import java.util.List;

public interface ProdutoRepository {
    List<Produto> findByTipoVinho(String tipoVinho);
}
