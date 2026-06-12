package br.edu.ifsp.trabalho.api_rest.repository;

import br.edu.ifsp.trabalho.api_rest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<UserDetails> findByLogin(String login);
    Optional<Usuario> findByEmail(String email);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
