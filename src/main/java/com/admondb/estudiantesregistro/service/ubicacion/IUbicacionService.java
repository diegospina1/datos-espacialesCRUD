package com.admondb.estudiantesregistro.service.ubicacion;

import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.model.Ubicacion;

import java.util.List;

public interface IUbicacionService {
    Ubicacion crearUbicacion(UbicacionDTO ubicacion);
    List<Ubicacion> verUbicaciones();
    Ubicacion buscarPorId(Long id);
}
