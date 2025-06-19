package br.com.wineservice.adapter.in.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClienteCompraDTOTest {

    @Test
    @DisplayName("Teste de sucesso do ClienteCompraDTO")
    void testClienteCompraDTO() {
        ClienteCompraDTO cliente = new ClienteCompraDTO();
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setCompras(new ArrayList<>());

        assertEquals("João", cliente.getNome());
        assertEquals("12345678900", cliente.getCpf());
        assertNotNull(cliente.getCompras());
        assertTrue(cliente.getCompras().isEmpty());
    }

    @Test
    @DisplayName("Teste de falha para ClienteCompraDTO")
    void testFalhaClienteCompraDTO() {
        ClienteCompraDTO cliente = new ClienteCompraDTO();
        cliente.setNome("João");
        cliente.setCpf("12345678900");
        cliente.setCompras(new ArrayList<>());

        assertNotEquals("Maria", cliente.getNome());
        assertNotEquals("98765432100", cliente.getCpf());
        assertNotEquals(null, cliente.getCompras());
    }

    @Test
    @DisplayName("Teste com lista de compras preenchida para ClienteCompraDTO")
    void testListaComprasPreenchidaClienteCompraDTO() {
        ClienteCompraDTO cliente = new ClienteCompraDTO();
        cliente.setCompras(Arrays.asList(new CompraItemDTO(), new CompraItemDTO()));

        assertNotNull(cliente.getCompras());
        assertEquals(2, cliente.getCompras().size());
    }
}

