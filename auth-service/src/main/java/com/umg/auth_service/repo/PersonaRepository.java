package com.umg.auth_service.repo;

import com.umg.auth_service.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}

