package br.edu.ifsp.trabalho.api_rest.controller;

import br.edu.ifsp.trabalho.api_rest.dto.LoginRequestDto;
import br.edu.ifsp.trabalho.api_rest.dto.LoginResponseDto;
import br.edu.ifsp.trabalho.api_rest.dto.RegistroRequestDto;
import br.edu.ifsp.trabalho.api_rest.model.Usuario;
import br.edu.ifsp.trabalho.api_rest.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto dto) {
        LoginResponseDto response = authenticationService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registro(@Valid @RequestBody RegistroRequestDto dto, UriComponentsBuilder uriBuilder) {
        Usuario usuario = authenticationService.registro(dto);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
}
