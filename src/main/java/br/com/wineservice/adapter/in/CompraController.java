package br.com.wineservice.adapter.in;

import br.com.wineservice.adapter.in.dto.ClienteFielDTO;
import br.com.wineservice.adapter.in.dto.CompraDetalheDTO;
import br.com.wineservice.adapter.in.dto.ProdutoDTO;
import br.com.wineservice.domain.port.in.CompraUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CompraController {

    private final CompraUseCase useCase;

    public CompraController(CompraUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping("/compras")
    public List<CompraDetalheDTO> listarCompras() {
        return useCase.listarCompras();
    }

    @GetMapping("/maior-compra/{ano}")
    public Map<String, Object> maiorCompra(@PathVariable int ano) {
        return useCase.maiorCompraDoAno(ano);
    }

    @GetMapping("/clientes-fieis")
    public List<ClienteFielDTO> clientesFieis() {
        return useCase.consultarClientesFieis();
    }

    @GetMapping("/recomendacao/cliente/tipo/{cpf}")
    public List<ProdutoDTO> recomendacaoPorTipo(@PathVariable String cpf) {
        return useCase.findByTipoVinho(cpf).stream()
                .map(produto -> new ProdutoDTO(
                        produto.getCodigo(),
                        produto.getNome(),
                        produto.getTipoVinho(),
                        produto.getSafra(),
                        produto.getPreco(),
                        produto.getAnoCompra()
                ))
                .collect(Collectors.toList());
    }
}
