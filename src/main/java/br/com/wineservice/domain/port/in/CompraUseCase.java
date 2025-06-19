package br.com.wineservice.domain.port.in;

import br.com.wineservice.adapter.in.dto.ClienteFielDTO;
import br.com.wineservice.adapter.in.dto.CompraDetalheDTO;
import br.com.wineservice.domain.model.Produto;

import java.util.List;
import java.util.Map;

public interface CompraUseCase {
    List<CompraDetalheDTO> listarCompras();

    List<ClienteFielDTO> consultarClientesFieis();

    List<Produto> findByTipoVinho(String cpf);

    Map<String, Object> maiorCompraDoAno(int ano);
}
