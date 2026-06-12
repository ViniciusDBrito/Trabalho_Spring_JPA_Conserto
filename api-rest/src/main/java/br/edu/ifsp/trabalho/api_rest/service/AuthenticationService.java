package br.edu.ifsp.trabalho.api_rest.service;

import br.edu.ifsp.trabalho.api_rest.dto.LoginRequestDto;
import br.edu.ifsp.trabalho.api_rest.dto.LoginResponseDto;
import br.edu.ifsp.trabalho.api_rest.dto.RegistroRequestDto;
import br.edu.ifsp.trabalho.api_rest.model.Usuario;
import br.edu.ifsp.trabalho.api_rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto dto) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.login(), dto.senha())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);

        return new LoginResponseDto(token);
    }

    public Usuario registro(RegistroRequestDto dto) throws IllegalArgumentException {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new IllegalArgumentException("Login já existe");
        }

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já existe");
        }

        Usuario usuario = Usuario.builder()
                .login(dto.login())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .ativo(true)
                .build();

        return usuarioRepository.save(usuario);
    }
}
