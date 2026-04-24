package br.edu.ifsp.trabalho.api_rest.service;

import br.edu.ifsp.trabalho.api_rest.dto.ConsertoRequestDto;
import br.edu.ifsp.trabalho.api_rest.dto.ConsertoResumoDto;
import br.edu.ifsp.trabalho.api_rest.dto.ConsertoUpdateDto;
import br.edu.ifsp.trabalho.api_rest.model.Conserto;
import br.edu.ifsp.trabalho.api_rest.model.Mecanico;
import br.edu.ifsp.trabalho.api_rest.model.Veiculo;
import br.edu.ifsp.trabalho.api_rest.repository.ConsertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsertoService {

    @Autowired
    private ConsertoRepository repository;

    public Conserto create(ConsertoRequestDto dto) {
        Mecanico mecanico = Mecanico.builder()
                .nome(dto.getMecanico().getNome())
                .anosExperiencia(dto.getMecanico().getAnosExperiencia())
                .build();

        Veiculo veiculo = Veiculo.builder()
                .marca(dto.getVeiculo().getMarca())
                .modelo(dto.getVeiculo().getModelo())
                .ano(dto.getVeiculo().getAno())
                .cor(dto.getVeiculo().getCor())
                .build();

        Conserto c = Conserto.builder()
                .dataEntrada(dto.getDataEntrada())
                .dataSaida(dto.getDataSaida())
                .mecanico(mecanico)
                .veiculo(veiculo)
                .ativo(true)
                .build();

        return repository.save(c);
    }

    public Page<Conserto> listAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<ConsertoResumoDto> listResumoAtivos() {
        return repository.findResumoAtivos();
    }

    public Optional<Conserto> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Conserto> updatePartial(Long id, ConsertoUpdateDto dto) {
        Optional<Conserto> opt = repository.findById(id);
        if (opt.isEmpty()) return Optional.empty();

        Conserto c = opt.get();
        if (dto.getDataSaida() != null) c.setDataSaida(dto.getDataSaida());
        if (dto.getMecanico() != null) {
            if (c.getMecanico() == null) c.setMecanico(new Mecanico());
            if (dto.getMecanico().getNome() != null) c.getMecanico().setNome(dto.getMecanico().getNome());
            if (dto.getMecanico().getAnosExperiencia() != null) c.getMecanico().setAnosExperiencia(dto.getMecanico().getAnosExperiencia());
        }

        Conserto saved = repository.save(c);
        return Optional.of(saved);
    }

    public boolean logicalDelete(Long id) {
        Optional<Conserto> opt = repository.findById(id);
        if (opt.isEmpty()) return false;
        Conserto c = opt.get();
        c.setAtivo(false);
        repository.save(c);
        return true;
    }
}
