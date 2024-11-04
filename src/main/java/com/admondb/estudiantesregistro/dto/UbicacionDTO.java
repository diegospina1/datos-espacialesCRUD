package com.admondb.estudiantesregistro.dto;

import org.locationtech.jts.geom.Point;

public record UbicacionDTO(
        Long id,
        Double longitud,
        Double latitud
) {
}
