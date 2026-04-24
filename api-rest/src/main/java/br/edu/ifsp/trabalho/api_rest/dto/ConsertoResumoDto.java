package br.edu.ifsp.trabalho.api_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsertoResumoDto {
    private Long id;
    private String dataEntrada;
    private String dataSaida;
    private String mecanicoNome;
    private String veiculoMarca;
    private String veiculoModelo;
}
