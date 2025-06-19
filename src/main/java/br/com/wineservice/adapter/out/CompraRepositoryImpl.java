package br.com.wineservice.adapter.out;

import br.com.wineservice.adapter.in.dto.ClienteCompraDTO;
import br.com.wineservice.domain.model.ClienteCompra;
import br.com.wineservice.domain.model.CompraItem;
import br.com.wineservice.domain.model.Produto;
import br.com.wineservice.domain.port.out.CompraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Repository
public class CompraRepositoryImpl implements CompraRepository {

    private static final Logger logger = LoggerFactory.getLogger(CompraRepositoryImpl.class);

    @Override
    public List<ClienteCompra> consultarClientesComCompras() {
        List<ClienteCompraDTO> dtos = lerClientesDoJson();
        return carregarClientesComCompras(dtos);
    }

    public List<ClienteCompraDTO> lerClientesDoJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/json/compras.json")) {
            if (is == null) {
                logger.error("Arquivo JSON não encontrado em /json/compras.json");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Arquivo JSON não encontrado");
            }
            return Arrays.asList(mapper.readValue(is, ClienteCompraDTO[].class));
        } catch (IOException e) {
            logger.error("Erro ao ler o arquivo JSON", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler JSON", e);
        }
    }

    private List<ClienteCompra> carregarClientesComCompras(List<ClienteCompraDTO> dtos) {
        return dtos.stream().map(dto -> {
            ClienteCompra cliente = new ClienteCompra();
            cliente.setNome(dto.getNome());
            cliente.setCpf(dto.getCpf());
            List<CompraItem> itens = dto.getCompras().stream().map(compraDTO -> {
                CompraItem item = new CompraItem();
                Produto produto = new Produto();
                produto.setCodigo(compraDTO.getCodigo()); // Utilizando o parâmetro 'codigo' diretamente
                item.setProduto(produto);
                item.setQuantidade(compraDTO.getQuantidade());
                return item;
            }).toList();
            cliente.setCompras(itens);
            return cliente;
        }).toList();
    }

}