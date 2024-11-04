package com.admondb.estudiantesregistro.dao.estudianteDAO;

import com.admondb.estudiantesregistro.dao.repository.EstudianteRepository;
import com.admondb.estudiantesregistro.model.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstudianteDAO implements IEstudianteDAO{

    private final EstudianteRepository repository;

    public EstudianteDAO(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public List<Estudiante> verEstudiantes() {
        return repository.findAll();
    }

    @Override
    public Estudiante verEstudiante(String cedula) {
        return repository.findByCedula(cedula);
    }

    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        repository.eliminarEstudiante(estudiante);
    }
}
