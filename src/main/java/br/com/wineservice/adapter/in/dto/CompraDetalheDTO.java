package br.com.wineservice.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import br.com.wineservice.domain.model.Produto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalhamento de uma compra individual")
public class CompraDetalheDTO {
    private String nome;

    private String cpf;

    private Produto produto;

    private int quantidade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private double totalItem;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private double totalCompra;
}
