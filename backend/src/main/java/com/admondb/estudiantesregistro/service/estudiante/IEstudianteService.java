package com.admondb.estudiantesregistro.service.estudiante;

import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.Estudiante;

import java.util.List;

public interface IEstudianteService {
    Estudiante crearEstudiante(EstudianteDTO estudianteDTO);
    List<Estudiante> verEstudiantes();
    Estudiante verEstudiante(String cedula);
    Estudiante actualizarEstudiante(ActualizarEstudianteDTO estudianteDTO);
    void eliminarEstudiante(String cedula);

}
