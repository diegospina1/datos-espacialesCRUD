package com.admondb.estudiantesregistro.service.estudiante;

import com.admondb.estudiantesregistro.dao.estudianteDAO.IEstudianteDAO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.Estudiante;
import org.springframework.stereotype.Service;
import static com.admondb.estudiantesregistro.mapper.EstudianteMapper.estudianteMapper;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService{

    private final IEstudianteDAO dao;

    public EstudianteService(IEstudianteDAO dao) {
        this.dao = dao;
    }

    @Override
    public Estudiante crearEstudiante(EstudianteDTO estudianteDTO) {
        return dao.crearEstudiante(estudianteMapper.toEstudiante(estudianteDTO));
    }

    @Override
    public List<Estudiante> verEstudiantes() {
        return dao.verEstudiantes();
    }

    @Override
    public Estudiante verEstudiante(String cedula) {
        return dao.verEstudiante(cedula);
    }

    @Override
    public Estudiante actualizarEstudiante(ActualizarEstudianteDTO datosActualizar) {
        Estudiante estudiante = verEstudiante(datosActualizar.cedula());
        estudiante.actualizar(datosActualizar);
        return dao.actualizarEstudiante(estudiante);
    }

    @Override
    public void eliminarEstudiante(String cedula) {
        Estudiante estudiante = verEstudiante(cedula);
        dao.eliminarEstudiante(estudiante);
    }
}
