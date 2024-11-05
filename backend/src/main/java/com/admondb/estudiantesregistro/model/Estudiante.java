package com.admondb.estudiantesregistro.model;

import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Setter
@Table(name = "estudiantes")
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Size(max = 255)
    @NotNull
    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Size(max = 255)
    @NotNull
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @OneToMany(mappedBy = "estudiante")
    private List<Direccion> direcciones;

    public Estudiante(String cedula, String nombres, String apellidos) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public void actualizar(ActualizarEstudianteDTO datosActualizar) {
        if(datosActualizar.nombres() != null) this.nombres = datosActualizar.nombres();
        if(datosActualizar.apellidos() != null) this.apellidos = datosActualizar.apellidos();
    }
}