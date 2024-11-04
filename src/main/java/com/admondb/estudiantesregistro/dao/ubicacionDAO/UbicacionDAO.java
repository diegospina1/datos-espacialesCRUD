package com.admondb.estudiantesregistro.dao.ubicacionDAO;

import com.admondb.estudiantesregistro.dao.repository.UbicacionRepository;
import com.admondb.estudiantesregistro.model.Ubicacion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UbicacionDAO implements IUbicacionDAO{

    private final UbicacionRepository repository;

    public UbicacionDAO(UbicacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ubicacion crear(Ubicacion ubicacion) {
        return repository.save(ubicacion);
    }

    @Override
    public List<Ubicacion> verTodas() {
        return repository.findAll();
    }

    @Override
    public Ubicacion buscarPorId(Long id) {
        return repository.getReferenceById(id);
    }
}
