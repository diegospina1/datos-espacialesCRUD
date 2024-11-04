package com.admondb.estudiantesregistro.dao.direccionDAO;

import com.admondb.estudiantesregistro.dao.repository.DireccionRepository;
import com.admondb.estudiantesregistro.model.CategoriaDireccion;
import com.admondb.estudiantesregistro.model.Direccion;
import com.admondb.estudiantesregistro.model.Estudiante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DireccionDAO implements IDireccionDAO{

    private final DireccionRepository repository;

    public DireccionDAO(DireccionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Direccion crearDireccion(Direccion direccion) {
        return repository.save(direccion);
    }

    @Override
    public List<Direccion> verDirecciones() {
        return repository.findAll();
    }

    @Override
    public Direccion verDireccionResidencia(Long idEstudiante) {
        return repository.encontrarResidencia(idEstudiante, CategoriaDireccion.RESIDENCIA);
    }

    @Override
    public Direccion verDireccionTrabajo(Long idEstudiante) {
        return repository.encontrarTrabajo(idEstudiante, CategoriaDireccion.TRABAJO);
    }

    @Override
    public Direccion actualizarDireccion(Direccion direccion) {
        return repository.save(direccion);
    }

    @Override
    public void eliminarDireccion(Direccion direccion) {
        repository.eliminarDireccion(direccion);
    }
}
