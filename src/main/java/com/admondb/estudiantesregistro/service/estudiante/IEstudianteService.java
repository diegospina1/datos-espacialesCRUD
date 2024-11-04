package com.admondb.estudiantesregistro.service.estudiante;

import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.Estudiante;

import java.util.List;

public interface IEstudianteService {
    EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO);
    List<EstudianteDTO> verEstudiantes();
    EstudianteDTO verEstudiante(String cedula);
    EstudianteDTO actualizarEstudiante(ActualizarEstudianteDTO estudianteDTO);
    void eliminarEstudiante(String cedula);

    Estudiante buscarEstudiantePorCedula(String cedula);
}
