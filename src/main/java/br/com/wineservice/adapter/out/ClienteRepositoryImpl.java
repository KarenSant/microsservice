package br.com.wineservice.adapter.out;

import br.com.wineservice.domain.model.ClienteCompra;
import br.com.wineservice.domain.port.out.ClienteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositoryImpl extends JpaRepository<ClienteCompra, Long>, ClienteRepository {
    Optional<ClienteCompra> findByCpf(String cpf);
}