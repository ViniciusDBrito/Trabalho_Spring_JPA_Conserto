package br.edu.ifsp.trabalho.api_rest.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conserto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conserto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_entrada", length = 10)
    private String dataEntrada;

    @Column(name = "data_saida", length = 10)
    private String dataSaida;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "nome", column = @Column(name = "mecanico_nome")),
        @AttributeOverride(name = "anosExperiencia", column = @Column(name = "mecanico_anos_experiencia"))
    })
    private Mecanico mecanico;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "marca", column = @Column(name = "veiculo_marca")),
        @AttributeOverride(name = "modelo", column = @Column(name = "veiculo_modelo")),
        @AttributeOverride(name = "ano", column = @Column(name = "veiculo_ano")),
        @AttributeOverride(name = "cor", column = @Column(name = "veiculo_cor"))
    })
    private Veiculo veiculo;

    @Column(name = "ativo")
    @lombok.Builder.Default
    private Boolean ativo = true;
}
