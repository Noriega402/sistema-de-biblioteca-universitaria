package com.umg.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
/**
 * DTO para el inicio de sesión. Contiene las credenciales enviadas por el
 * cliente.
 */
public record LoginRequest(
        @NotBlank(message = "El emial es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        String password
        ) {

}