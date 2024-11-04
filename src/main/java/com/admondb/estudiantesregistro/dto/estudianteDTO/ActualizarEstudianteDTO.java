package com.admondb.estudiantesregistro.dto.estudianteDTO;

import jakarta.validation.constraints.NotBlank;

public record ActualizarEstudianteDTO(
        @NotBlank
        String cedula,
        String nombres,
        String apellidos
) {
}
