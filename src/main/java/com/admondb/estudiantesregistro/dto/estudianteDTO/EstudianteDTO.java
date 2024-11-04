package com.admondb.estudiantesregistro.dto.estudianteDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EstudianteDTO(
        Long id,
        @NotBlank
        @Pattern(regexp = "^\\d{10}$")
        String cedula,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)?$")
        String nombres,
        @NotBlank
        @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)?$")
        String apellidos
) {
}
