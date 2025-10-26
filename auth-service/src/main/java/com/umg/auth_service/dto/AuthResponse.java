package com.umg.auth_service.dto;
/**
 * DTO de respuesta de autenticación.
 * Devuelve el token JWT generado y algunos datos del usuario autenticado.
 */
public record AuthResponse(
        String token,
        String rol,
        String nick,
        String email
) {}

