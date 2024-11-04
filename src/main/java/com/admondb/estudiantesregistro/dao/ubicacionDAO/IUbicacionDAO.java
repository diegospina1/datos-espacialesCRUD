package com.admondb.estudiantesregistro.dao.ubicacionDAO;

import com.admondb.estudiantesregistro.model.Ubicacion;

import java.util.List;

public interface IUbicacionDAO {
    Ubicacion crear(Ubicacion ubicacion);

    List<Ubicacion> verTodas();

    Ubicacion buscarPorId(Long id);
}
