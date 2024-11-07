package com.admondb.estudiantesregistro.service.direccion;

import com.admondb.estudiantesregistro.dto.ActualizarDatosDTO;
import com.admondb.estudiantesregistro.dto.DatosDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDistanciaDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface IDireccionService {
    void eliminar(String cedula);

    DatosDTO actualizar(@Valid ActualizarDatosDTO datos);

    DatosDTO verPorEstudiante(String cedula);

    List<DatosDTO> verTodas();

    DatosDTO crear(@Valid DatosDTO datos);

    List<EstudianteDistanciaDTO> obtenerEstudiantesConDistancia();
}
