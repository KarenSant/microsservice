package br.com.wineservice.domain.port.out;

import br.com.wineservice.domain.model.ClienteCompra;

import java.util.List;

public interface CompraRepository {
    List<ClienteCompra> consultarClientesComCompras();
}
