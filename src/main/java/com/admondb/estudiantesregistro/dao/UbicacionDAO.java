package com.admondb.estudiantesregistro.dao;

import com.admondb.estudiantesregistro.dao.repository.UbicacionRepository;
import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.model.Ubicacion;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UbicacionDAO {

    private final UbicacionRepository repository;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    public UbicacionDAO(UbicacionRepository repository) {
        this.repository = repository;
    }

    public UbicacionDTO crear(UbicacionDTO ubicacion) {
        Point coordenadas = geometryFactory.createPoint(new Coordinate(ubicacion.latitud(), ubicacion.longitud()));
        Ubicacion nuevaUbicacion = repository.save(new Ubicacion(null, coordenadas));
        return new UbicacionDTO(nuevaUbicacion.getId(), nuevaUbicacion.getCoordenadas().getY(), nuevaUbicacion.getCoordenadas().getX());
    }

    public List<UbicacionDTO> verTodas() {
        return repository.findAll().stream()
                .map(u -> new UbicacionDTO(u.getId(), u.getCoordenadas().getY(), u.getCoordenadas().getX()))
                .toList();
    }

    public Ubicacion buscarPorId(Long id) {
        return repository.getReferenceById(id);
    }
}
