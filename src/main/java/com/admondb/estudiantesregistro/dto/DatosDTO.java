package com.admondb.estudiantesregistro.dto;

import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import jakarta.validation.Valid;

public record DatosDTO(
        @Valid
        EstudianteDTO estudiante,
        UbicacionDTO residencia,
        UbicacionDTO trabajo
) {
}
