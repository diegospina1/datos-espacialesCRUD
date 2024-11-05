package com.admondb.estudiantesregistro.dto;

import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import jakarta.validation.Valid;

public record ActualizarDatosDTO(
        @Valid
        ActualizarEstudianteDTO estudiante,
        UbicacionDTO residencia,
        UbicacionDTO trabajo
) {
}
