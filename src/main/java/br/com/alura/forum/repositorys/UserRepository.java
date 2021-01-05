package br.com.alura.forum.repositorys;

import br.com.alura.forum.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
