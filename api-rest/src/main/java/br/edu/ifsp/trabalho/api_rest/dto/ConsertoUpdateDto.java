package br.edu.ifsp.trabalho.api_rest.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsertoUpdateDto {
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Formato de data inválido (dd/MM/yyyy)")
    private String dataSaida;

    private MecanicoUpdateDto mecanico;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MecanicoUpdateDto {
        private String nome;
        private Integer anosExperiencia;
    }
}
