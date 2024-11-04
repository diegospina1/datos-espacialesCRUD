package com.admondb.estudiantesregistro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "direcciones")
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Estudiante.class)
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Ubicacion.class)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @NotNull
    @Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaDireccion categoria;

    @NotNull
    @Column(name = "principal", nullable = false)
    private Boolean principal;

    public Direccion(Estudiante estudiante, Ubicacion ubicacion, CategoriaDireccion categoria){
        this.estudiante = estudiante;
        this.ubicacion = ubicacion;
        this.categoria = categoria;
        this.principal = true;
    }

    public void actualizar(Estudiante estudiante, Ubicacion ubicacion, CategoriaDireccion categoria){
        if (estudiante != null) this.estudiante = estudiante;
        if (ubicacion != null) this.ubicacion = ubicacion;
        if (categoria != null) this.categoria = categoria;
    }

}