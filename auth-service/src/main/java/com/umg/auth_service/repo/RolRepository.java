package com.umg.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.umg.auth_service.entity.Rol;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByDescripcion(String descripcion);
}