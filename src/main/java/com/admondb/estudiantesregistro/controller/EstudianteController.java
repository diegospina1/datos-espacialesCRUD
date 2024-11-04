package com.admondb.estudiantesregistro.controller;

import com.admondb.estudiantesregistro.dto.estudianteDTO.ActualizarEstudianteDTO;
import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.service.estudiante.IEstudianteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("estudiantes")
public class EstudianteController {

    private final IEstudianteService service;

    public EstudianteController(IEstudianteService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody @Valid EstudianteDTO datos){
        return ResponseEntity.ok(service.crearEstudiante(datos));
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> verEstudiantes(){
        return ResponseEntity.ok(service.verEstudiantes());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<EstudianteDTO> verEstudiantes(@PathVariable("cedula") String cedula){
        return ResponseEntity.ok(service.verEstudiante(cedula));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@RequestBody @Valid ActualizarEstudianteDTO datos){
        return ResponseEntity.ok(service.actualizarEstudiante(datos));
    }

    @DeleteMapping("/{cedula}")
    @Transactional
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable("cedula") String cedula){
        service.eliminarEstudiante(cedula);
        return ResponseEntity.noContent().build();
    }
}
