package br.com.wineservice.adapter.out;

import br.com.wineservice.domain.model.Produto;
import br.com.wineservice.domain.port.out.ProdutoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositoryImpl extends JpaRepository<Produto, Long>, ProdutoRepository {
    List<Produto> findByTipoVinho(String tipoVinho);
}