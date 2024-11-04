package com.admondb.estudiantesregistro.service.estudiante;

import com.admondb.estudiantesregistro.dao.estudianteDAO.IEstudianteDAO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService{

    private final IEstudianteDAO dao;

    public EstudianteService(IEstudianteDAO dao) {
        this.dao = dao;
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = dao.crearEstudiante(new Estudiante(estudianteDTO.cedula(), estudianteDTO.nombres(), estudianteDTO.apellidos()));
        return new EstudianteDTO(estudiante.getId(), estudiante.getCedula(), estudiante.getNombres(), estudiante.getApellidos());
    }

    @Override
    public List<EstudianteDTO> verEstudiantes() {
        return dao.verEstudiantes().stream()
                .map(e -> new EstudianteDTO(e.getId(), e.getCedula(), e.getNombres(), e.getApellidos()))
                .toList();
    }

    @Override
    public EstudianteDTO verEstudiante(String cedula) {
        Estudiante estudiante = dao.verEstudiante(cedula);
        return new EstudianteDTO(estudiante.getId(), estudiante.getCedula(), estudiante.getNombres(), estudiante.getApellidos());
    }

    @Override
    public EstudianteDTO actualizarEstudiante(ActualizarEstudianteDTO datosActualizar) {
        Estudiante estudiante = dao.verEstudiante(datosActualizar.cedula());
        estudiante.actualizar(datosActualizar);
        Estudiante actualizado = dao.actualizarEstudiante(estudiante);

        return new EstudianteDTO(actualizado.getId(), actualizado.getCedula(), actualizado.getNombres(), actualizado.getApellidos());
    }

    @Override
    public void eliminarEstudiante(String cedula) {
        dao.eliminarEstudiante(cedula);
    }

    @Override
    public Estudiante buscarEstudiantePorCedula(String cedula) {
        return dao.verEstudiante(cedula);
    }
}
