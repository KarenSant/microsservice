package br.com.wineservice.domain.port.out;

import br.com.wineservice.domain.model.ClienteCompra;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Optional<ClienteCompra> findByCpf(String cpf);
}