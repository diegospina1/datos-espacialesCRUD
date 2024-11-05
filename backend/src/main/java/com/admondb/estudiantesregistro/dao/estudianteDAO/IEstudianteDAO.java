package com.admondb.estudiantesregistro.dao.estudianteDAO;

import com.admondb.estudiantesregistro.model.Estudiante;

import java.util.List;

public interface IEstudianteDAO {
    Estudiante crearEstudiante(Estudiante estudiante);
    List<Estudiante> verEstudiantes();
    Estudiante verEstudiante(String cedula);
    Estudiante actualizarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(Estudiante estudiante);
}
