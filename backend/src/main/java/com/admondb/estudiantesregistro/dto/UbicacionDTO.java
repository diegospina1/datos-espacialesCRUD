package com.admondb.estudiantesregistro.dto;

import org.locationtech.jts.geom.Point;

public record UbicacionDTO(
        Double longitud,
        Double latitud
) {
}
