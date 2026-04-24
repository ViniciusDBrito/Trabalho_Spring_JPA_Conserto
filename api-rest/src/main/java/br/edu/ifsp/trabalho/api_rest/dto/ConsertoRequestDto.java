package br.edu.ifsp.trabalho.api_rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsertoRequestDto {
    @NotBlank
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Formato de data inválido (dd/MM/yyyy)")
    private String dataEntrada;

    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Formato de data inválido (dd/MM/yyyy)")
    private String dataSaida;

    @NotNull
    @Valid
    private MecanicoDto mecanico;

    @NotNull
    @Valid
    private VeiculoDto veiculo;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MecanicoDto {
        @NotBlank
        private String nome;

        private Integer anosExperiencia;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VeiculoDto {
        @NotBlank
        private String marca;

        @NotBlank
        private String modelo;

        @NotBlank
        @Pattern(regexp = "^\\d{4}$", message = "Ano deve ter 4 dígitos")
        private String ano;

        private String cor;
    }
}
