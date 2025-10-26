package com.umg.auth_service.service;

import com.umg.auth_service.dto.AuthResponse;
import com.umg.auth_service.dto.LoginRequest;
import com.umg.auth_service.dto.RegistroRequest;
import com.umg.auth_service.entity.Persona;
import com.umg.auth_service.entity.Rol;
import com.umg.auth_service.entity.Usuario;
import com.umg.auth_service.repo.PersonaRepository;
import com.umg.auth_service.repo.RolRepository;
import com.umg.auth_service.repo.UsuarioRepository;
import com.umg.auth_service.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarios;
    private final PersonaRepository personas;
    private final RolRepository roles;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    @Transactional
    public AuthResponse register(RegistroRequest r) {
        if (usuarios.existsByNick(r.nick()) || usuarios.existsByEmail(r.email())) {
            throw new IllegalArgumentException("Usuario o email ya existe");
        }

        var persona = new Persona();
        persona.setNombre(r.nombre());
        persona.setApellido(r.apellido());
        persona.setTelefono(r.telefono());
        personas.save(persona);

        Rol rol = roles.findByDescripcion(Optional.ofNullable(r.rol()).orElse("ESTUDIANTE"))
                .orElseThrow(() -> new IllegalArgumentException("Rol inválido"));

        var user = Usuario.builder()
                .nick(r.nick())
                .email(r.email())
                .password(encoder.encode(r.password()))
                .persona(persona)
                .rol(rol)
                .creadoEl(OffsetDateTime.now())
                .build();
        usuarios.save(user);

        var token = jwt.generateToken(user.getNick(), Map.of("roles", rol.getDescripcion()));
        return new AuthResponse(token, rol.getDescripcion(), user.getNick(), user.getEmail());
    }

    @Transactional
    public AuthResponse login(LoginRequest r) {
        var user = usuarios.findByEmail(r.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!encoder.matches(r.password(), user.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        var token = jwt.generateToken(user.getNick(), Map.of("roles", user.getRol().getDescripcion()));
        return new AuthResponse(token, user.getRol().getDescripcion(), user.getNick(), user.getEmail());
    }
}