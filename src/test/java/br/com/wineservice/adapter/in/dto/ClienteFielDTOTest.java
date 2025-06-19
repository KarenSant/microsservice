

package br.com.wineservice.adapter.in.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteFielDTOTest {

    @Test
    @DisplayName("Teste de falha para ClienteFielDTO")
    void testFalhaClienteFielDTO() {
        ClienteFielDTO cliente = new ClienteFielDTO("Maria", "98765432100", 5, 1500.0);

        assertNotEquals("João", cliente.getNome());
        assertNotEquals("12345678900", cliente.getCpf());
        assertNotEquals(10, cliente.getQuantidadeCompras());
        assertNotEquals(2000.0, cliente.getValorTotalCompras());
    }

    @Test
    @DisplayName("Teste de sucesso para ClienteFielDTO")
    void testSucessoClienteFielDTO() {
        ClienteFielDTO cliente = new ClienteFielDTO("Maria", "98765432100", 5, 1500.0);

        // Teste de sucesso: valores corretos
        assertEquals("Maria", cliente.getNome());
        assertEquals("98765432100", cliente.getCpf());
        assertEquals(5, cliente.getQuantidadeCompras());
        assertEquals(1500.0, cliente.getValorTotalCompras());
    }
}

