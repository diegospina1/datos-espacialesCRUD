package com.admondb.estudiantesregistro.dao.repository;

import com.admondb.estudiantesregistro.model.CategoriaDireccion;
import com.admondb.estudiantesregistro.model.Direccion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Query("select d from Direccion d where d.estudiante.id = :#{#id} and d.categoria = :#{#trabajo}")
    Direccion encontrarTrabajo(Long id, CategoriaDireccion trabajo);

    @Query("select d from Direccion d where d.estudiante.id = :#{#id} and d.categoria = :#{#residencia}")
    Direccion encontrarResidencia(Long id, CategoriaDireccion residencia);

    @Modifying
    @Transactional
    @Query("DELETE FROM Direccion d WHERE d = :direccion")
    void eliminarDireccion(Direccion direccion);

    @Query(
            value = """
        select 
            e.nombres as nombres,
            e.apellidos as apellidos,
            ST_Distance_Sphere(r.coordenadas, t.coordenadas) as distancia
        from estudiantes e
        join direcciones dr on e.id = dr.estudiante_id and dr.categoria = 'RESIDENCIA'
        join direcciones dt on e.id = dt.estudiante_id and dt.categoria = 'TRABAJO'
        join ubicaciones r on dr.ubicacion_id = r.id
        join ubicaciones t on dt.ubicacion_id = t.id
        order by distancia asc
    """,
            nativeQuery = true
    )
    List<Object[]> obtenerEstudiantesDistancia();
}
