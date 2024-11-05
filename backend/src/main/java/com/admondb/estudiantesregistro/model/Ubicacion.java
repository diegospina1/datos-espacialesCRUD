package com.admondb.estudiantesregistro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Table(name = "ubicaciones")
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Point coordenadas;
}