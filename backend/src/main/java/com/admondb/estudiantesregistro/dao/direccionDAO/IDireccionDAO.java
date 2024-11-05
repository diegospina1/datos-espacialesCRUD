package com.admondb.estudiantesregistro.dao.direccionDAO;

import com.admondb.estudiantesregistro.model.Direccion;
import com.admondb.estudiantesregistro.model.Estudiante;

import java.util.List;

public interface IDireccionDAO {
    Direccion crearDireccion(Direccion direccion);
    List<Direccion> verDirecciones();
    Direccion verDireccionResidencia(Long idEstudiante);
    Direccion verDireccionTrabajo(Long idEstudiante);
    Direccion actualizarDireccion(Direccion direccion);
    void eliminarDireccion(Direccion direccion);

}
