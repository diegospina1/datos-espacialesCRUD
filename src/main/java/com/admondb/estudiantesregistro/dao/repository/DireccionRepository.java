package com.admondb.estudiantesregistro.dao.repository;

import com.admondb.estudiantesregistro.model.CategoriaDireccion;
import com.admondb.estudiantesregistro.model.Direccion;
import com.admondb.estudiantesregistro.model.Estudiante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Query("select d from Direccion d where d.estudiante.id = :#{#id} and d.categoria = :#{#trabajo}")
    Direccion encontrarTrabajo(Long id, CategoriaDireccion trabajo);

    @Query("select d from Direccion d where d.estudiante.id = :#{#id} and d.categoria = :#{#residencia}")
    Direccion encontrarResidencia(Long id, CategoriaDireccion residencia);

    @Modifying
    @Transactional
    @Query("DELETE FROM Direccion d WHERE d = :direccion")
    void eliminarDireccion(Direccion direccion);
}
