package com.admondb.estudiantesregistro.mapper;

import com.admondb.estudiantesregistro.dto.estudianteDTO.EstudianteDTO;
import com.admondb.estudiantesregistro.model.Estudiante;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T16:38:16-0500",
    comments = "version: 1.6.2, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class EstudianteMapperImpl implements EstudianteMapper {

    @Override
    public Estudiante toEstudiante(EstudianteDTO estudianteDTO) {
        if ( estudianteDTO == null ) {
            return null;
        }

        Estudiante estudiante = new Estudiante();

        estudiante.setId( estudianteDTO.id() );
        estudiante.setCedula( estudianteDTO.cedula() );
        estudiante.setNombres( estudianteDTO.nombres() );
        estudiante.setApellidos( estudianteDTO.apellidos() );

        return estudiante;
    }

    @Override
    public EstudianteDTO toEstudianteDTO(Estudiante estudiante) {
        if ( estudiante == null ) {
            return null;
        }

        Long id = null;
        String cedula = null;
        String nombres = null;
        String apellidos = null;

        id = estudiante.getId();
        cedula = estudiante.getCedula();
        nombres = estudiante.getNombres();
        apellidos = estudiante.getApellidos();

        EstudianteDTO estudianteDTO = new EstudianteDTO( id, cedula, nombres, apellidos );

        return estudianteDTO;
    }
}
