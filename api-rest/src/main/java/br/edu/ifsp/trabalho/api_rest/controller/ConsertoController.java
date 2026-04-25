package br.edu.ifsp.trabalho.api_rest.controller;

import br.edu.ifsp.trabalho.api_rest.dto.ConsertoRequestDto;
import br.edu.ifsp.trabalho.api_rest.dto.ConsertoResumoDto;
import br.edu.ifsp.trabalho.api_rest.dto.ConsertoUpdateDto;
import br.edu.ifsp.trabalho.api_rest.model.Conserto;
import br.edu.ifsp.trabalho.api_rest.service.ConsertoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consertos")
public class ConsertoController {

    @Autowired
    private ConsertoService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ConsertoRequestDto dto, UriComponentsBuilder uriBuilder) {
        try {
            Conserto saved = service.create(dto);
            URI uri = uriBuilder.path("/consertos/{id}").buildAndExpand(saved.getId()).toUri();
            return ResponseEntity.created(uri).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao registrar conserto."));
        }
    }

    @GetMapping
    public ResponseEntity<?> listAll(Pageable pageable) {
        Page<Conserto> page = service.listAll(pageable);
        if (page.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", "Nenhum conserto encontrado."));
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping("/resumo")
    public ResponseEntity<?> resumo() {
        List<ConsertoResumoDto> list = service.listResumoAtivos();
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", "Nenhum conserto ativo encontrado."));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return service.getById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("mensagem", "Conserto com id " + id + " não encontrado.")));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @Valid @RequestBody ConsertoUpdateDto dto) {
        try {
            return service.updatePartial(id, dto)
                    .<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("mensagem", "Conserto com id " + id + " não encontrado.")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("mensagem", "Erro ao atualizar conserto."));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> logicalDelete(@PathVariable Long id) {
        boolean ok = service.logicalDelete(id);

        if (!ok) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensagem", "Conserto com id " + id + " não encontrado."));
        }

        return ResponseEntity.noContent().build();
    }
}
