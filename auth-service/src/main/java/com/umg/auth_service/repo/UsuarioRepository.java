package com.umg.auth_service.repo;

import com.umg.auth_service.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNick(String nick);
    boolean existsByEmail(String email);
    boolean existsByNick(String nick);
}
