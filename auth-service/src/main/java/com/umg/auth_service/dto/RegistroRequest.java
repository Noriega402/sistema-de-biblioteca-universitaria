package com.umg.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
/**
 * DTO para registrar nuevos usuarios en el sistema.
 * Representa los datos que llegan desde el cliente en el registro.
 */
public record RegistroRequest(
        @NotBlank(message = "El nombre de usuario (nick) es obligatorio")
        String nick,
        @Email(message = "Debe ingresar un correo válido")
        @NotBlank(message = "El correo es obligatorio")
        String email,
        @NotBlank(message = "La contraseña es obligatoria")
        String password,
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
        String telefono,
        @NotBlank(message = "El rol es obligatorio")
        String rol // "ESTUDIANTE", "BIBLIOTECARIO", "ADMIN"
        ) {

}