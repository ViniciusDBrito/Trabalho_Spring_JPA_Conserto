package br.edu.ifsp.trabalho.api_rest.repository;

import br.edu.ifsp.trabalho.api_rest.dto.ConsertoResumoDto;
import br.edu.ifsp.trabalho.api_rest.model.Conserto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsertoRepository extends JpaRepository<Conserto, Long> {

    @Query("select new br.edu.ifsp.trabalho.api_rest.dto.ConsertoResumoDto(c.id, c.dataEntrada, c.dataSaida, c.mecanico.nome, c.veiculo.marca, c.veiculo.modelo) from Conserto c where c.ativo = true")
    List<ConsertoResumoDto> findResumoAtivos();

    Page<Conserto> findAll(Pageable pageable);
}
