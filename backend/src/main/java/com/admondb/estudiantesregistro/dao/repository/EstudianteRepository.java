package com.admondb.estudiantesregistro.dao.repository;

import com.admondb.estudiantesregistro.model.Estudiante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    Estudiante findByCedula(String cedula);

    @Modifying
    @Transactional
    @Query("delete from Estudiante e where e = :estudiante")
    void eliminarEstudiante(Estudiante estudiante);
}
