package com.admondb.estudiantesregistro.service.ubicacion;

import com.admondb.estudiantesregistro.dao.ubicacionDAO.IUbicacionDAO;
import com.admondb.estudiantesregistro.dto.UbicacionDTO;
import com.admondb.estudiantesregistro.model.Ubicacion;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService implements IUbicacionService{

    private final IUbicacionDAO dao;
    private final GeometryFactory geometryFactory = new GeometryFactory();

    public UbicacionService(IUbicacionDAO dao) {
        this.dao = dao;
    }

    @Override
    public Ubicacion crearUbicacion(UbicacionDTO ubicacion){
        Point coordenadas = geometryFactory.createPoint(new Coordinate(ubicacion.latitud(), ubicacion.longitud()));
        Ubicacion nuevaUbicacion =  new Ubicacion(null, coordenadas);
        return dao.crear(nuevaUbicacion);
    }

    @Override
    public List<Ubicacion> verUbicaciones(){
        return dao.verTodas();
    }

    @Override
    public Ubicacion buscarPorId(Long id){
        return dao.buscarPorId(id);
    }
}
