package br.edu.ifsp.trabalho.api_rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mecanico {
    @Column(name = "nome")
    private String nome;

    @Column(name = "anos_experiencia")
    private Integer anosExperiencia;
}
