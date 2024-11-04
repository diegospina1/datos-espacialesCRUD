package com.admondb.estudiantesregistro.controller;

import com.admondb.estudiantesregistro.dto.ActualizarDatosDTO;
import com.admondb.estudiantesregistro.dto.DatosDTO;
import com.admondb.estudiantesregistro.service.direccion.IDireccionService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class DireccionController {

    private final IDireccionService service;

    public DireccionController(IDireccionService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDTO> crear(@RequestBody @Valid DatosDTO datos){
        return ResponseEntity.ok(service.crear(datos));
    }

    @GetMapping
    public ResponseEntity<List<DatosDTO>> verTodas(){
        return ResponseEntity.ok(service.verTodas());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<DatosDTO> verPorEstudiante(@PathVariable("cedula") String cedula){
        return ResponseEntity.ok(service.verPorEstudiante(cedula));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDTO> actualizar(@RequestBody @Valid ActualizarDatosDTO datos){
        return ResponseEntity.ok(service.actualizar(datos));
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<DatosDTO> eliminar(@PathVariable("cedula") String cedula){
        service.eliminar(cedula);
        return ResponseEntity.noContent().build();
    }
}
