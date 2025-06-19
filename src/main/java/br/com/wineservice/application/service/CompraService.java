package br.com.wineservice.application.service;

import br.com.wineservice.adapter.in.dto.ClienteCompraDTO;
import br.com.wineservice.adapter.in.dto.ClienteFielDTO;
import br.com.wineservice.adapter.in.dto.CompraDetalheDTO;
import br.com.wineservice.adapter.in.dto.ProdutoDTO;
import br.com.wineservice.domain.model.ClienteCompra;
import br.com.wineservice.domain.model.CompraItem;
import br.com.wineservice.domain.model.Produto;
import br.com.wineservice.domain.port.in.CompraUseCase;
import br.com.wineservice.domain.port.out.ClienteRepository;
import br.com.wineservice.domain.port.out.CompraRepository;
import br.com.wineservice.domain.port.out.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService implements CompraUseCase {

    private final CompraRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    private double totalCompra(ClienteCompra cc) {
        return cc.getCompras().stream().mapToDouble(i -> i.getProduto().getPreco() * i.getQuantidade()).sum();
    }

    @Override
    public List<CompraDetalheDTO> listarCompras() {
        return repository.consultarClientesComCompras().stream()
                .flatMap(cc -> cc.getCompras().stream().map(i -> new CompraDetalheDTO(
                        cc.getNome(),
                        cc.getCpf(),
                        i.getProduto(),
                        i.getQuantidade(),
                        i.getProduto().getPreco() * i.getQuantidade(),
                        totalCompra(cc)
                )))
                .sorted(Comparator.comparingDouble(CompraDetalheDTO::getTotalCompra))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> maiorCompraDoAno(int ano) {
        return repository.consultarClientesComCompras().stream()
                .map(cc -> {
                    List<CompraItem> itensAno = cc.getCompras().stream().filter(i -> i.getProduto().getAnoCompra() == ano).toList();
                    double total = itensAno.stream().mapToDouble(i -> i.getProduto().getPreco() * i.getQuantidade()).sum();
                    return Map.of("nome", cc.getNome(), "cpf", cc.getCpf(), "compras", itensAno, "total", total);
                })
                .max(Comparator.comparingDouble(m -> (double) m.get("total")))
                .orElse(Map.of());
    }

    @Override
    public List<ClienteFielDTO> consultarClientesFieis() {
        return repository.consultarClientesComCompras().stream()
                .map(cc -> new ClienteFielDTO(
                        cc.getNome(),
                        cc.getCpf(),
                        cc.getCompras().size(),
                        totalCompra(cc)
                ))
                .sorted(Comparator.comparing(ClienteFielDTO::getQuantidadeCompras).reversed()
                        .thenComparing(ClienteFielDTO::getValorTotalCompras, Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Produto> findByTipoVinho(String cpf) {
        ClienteCompra cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado: " + cpf));

        Map<String, Long> tiposDeVinho = cliente.getCompras().stream()
                .map(item -> item.getProduto().getTipoVinho())
                .collect(Collectors.groupingBy(tipo -> tipo, Collectors.counting()));

        String tipoMaisComprado = tiposDeVinho.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum tipo de vinho encontrado"));

        return produtoRepository.findByTipoVinho(tipoMaisComprado);
    }


    public List<ClienteCompraDTO> lerClientesDoJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/json/compras.json")) {
            return Arrays.asList(mapper.readValue(is, ClienteCompraDTO[].class));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler JSON", e);
        }
    }

    private List<ClienteCompra> carregarClientesComCompras() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = getClass().getResourceAsStream("/json/compras.json")) {
            ClienteCompraDTO[] dtos = mapper.readValue(is, ClienteCompraDTO[].class);
            return Arrays.stream(dtos).map(dto -> {
                ClienteCompra cliente = new ClienteCompra();
                cliente.setNome(dto.getNome());
                cliente.setCpf(dto.getCpf());

                List<CompraItem> itens = dto.getCompras().stream().map(itemDto -> {
                    CompraItem item = new CompraItem();
                    Produto produto = new Produto();
                    produto.setCodigo(itemDto.getCodigo());
                    produto.setNome("Nome padrão");
                    produto.setTipoVinho("Tipo padrão");
                    produto.setSafra("Safra padrão");
                    produto.setPreco(0.0);
                    produto.setAnoCompra(2023);
                    item.setProduto(produto);
                    item.setQuantidade(itemDto.getQuantidade());
                    return item;
                }).collect(Collectors.toList());

                cliente.setCompras(itens);
                return cliente;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao ler JSON", e);
        }
    }


}